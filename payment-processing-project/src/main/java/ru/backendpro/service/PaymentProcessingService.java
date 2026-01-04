package ru.backendpro.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.backendpro.dao.repository.PaymentRepository;
import ru.backendpro.entity.Payment;
import ru.backendpro.entity.PaymentStatus;
import ru.backendpro.exception.AccountLockTimeoutException;
import ru.backendpro.exception.BusinessException;
import ru.backendpro.exception.ResourceNotFoundException;

import java.time.Clock;
import java.time.ZonedDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentProcessingService {

    @Value("${app.payment.processing.batch-size:10}")
    private int batchSize;

    private final PaymentRepository paymentRepository;
    private final AccountService accountService;
    private final PaymentValidator paymentValidator;
    private final Clock clock;

    @Scheduled(fixedRateString = "${app.payment.processing.rate:5000}", initialDelay = 5000)
    @Transactional
    public void processPendingPayments() {
        List<Payment> pendingPayments = paymentRepository.findPaymentsForProcessing(PaymentStatus.PENDING.getId(),
                batchSize);

        if (pendingPayments.isEmpty()) {
            log.debug("No pending payments to process");
            return;
        }

        log.info("Processing {} pending payment(s)", pendingPayments.size());
        pendingPayments.stream()
                .map(Payment::getId)
                .forEach(id -> log.info("Payment ID: {}", id));

        for (Payment payment : pendingPayments) {
            try {
                processPayment(payment.getId());
                log.info("Successfully processed payment with id: {}", payment.getId());
            } catch (AccountLockTimeoutException e) {
                log.warn("Account lock timeout for payment with id: {}. Payment will be processed later. Error: {}",
                        payment.getId(), e.getMessage());
                // Платеж остается в статусе PENDING для повторной обработки
            } catch (Exception e) {
                log.error("Failed to process payment with id: {}. Error: {}", payment.getId(), e.getMessage());
            }
        }
    }

    private void processPayment(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with id: " + paymentId));

        if (payment.getStatus().isFinalStatus()) {
            throw new BusinessException("Cannot process payment in final status: " + payment.getStatus());
        }

        paymentValidator.validatePaymentTransfer(
                payment.getSourceAccount(),
                payment.getTargetAccount(),
                payment.getAmount(),
                payment.getCurrencyCode()
        );

        try {
            accountService.updateBalance(payment.getSourceAccount().getAccountNumber(), payment.getAmount().negate());

            accountService.updateBalance(payment.getTargetAccount().getAccountNumber(), payment.getAmount());

            payment.setStatus(PaymentStatus.COMPLETED);
            payment.setProcessedAt(ZonedDateTime.now(clock));

            // TODO: Отправить событие в Kafka об успешной обработке платежа (payment.completed)
        } catch (AccountLockTimeoutException e) {
            // При timeout блокировки платеж остается в PENDING для повторной обработки
            log.warn("Account lock timeout during payment processing for payment id: {}. Payment will be retried later.",
                    paymentId);
            throw e;
        } catch (Exception e) {
            payment.setStatus(PaymentStatus.FAILED);
            payment.setProcessedAt(ZonedDateTime.now(clock));
            paymentRepository.save(payment);

            // TODO: Отправить событие в Kafka о неудачной обработке платежа (payment.failed)

            throw new BusinessException("Payment processing failed: " + e.getMessage());
        }

        paymentRepository.save(payment);
    }
}
