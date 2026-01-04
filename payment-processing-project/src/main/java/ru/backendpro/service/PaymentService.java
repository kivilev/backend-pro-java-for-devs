package ru.backendpro.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.backendpro.controller.dto.PaymentCreateRequestDto;
import ru.backendpro.controller.dto.PaymentSummaryDto;
import ru.backendpro.controller.dto.PaymentUpdateRequestDto;
import ru.backendpro.entity.Account;
import ru.backendpro.entity.Payment;
import ru.backendpro.entity.PaymentDataField;
import ru.backendpro.entity.PaymentDataFieldEnum;
import ru.backendpro.entity.PaymentDetail;
import ru.backendpro.entity.PaymentStatus;
import ru.backendpro.exception.BusinessException;
import ru.backendpro.exception.PaymentLockTimeoutException;
import ru.backendpro.exception.ResourceNotFoundException;
import ru.backendpro.dao.repository.AccountRepository;
import ru.backendpro.dao.repository.PaymentDataFieldRepository;
import ru.backendpro.dao.repository.PaymentRepository;

import jakarta.persistence.LockTimeoutException;
import jakarta.persistence.PessimisticLockException;
import java.time.Clock;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final AccountRepository accountRepository;
    private final PaymentDataFieldRepository paymentDataFieldRepository;
    private final PaymentValidator paymentValidator;
    private final Clock clock;

    @Transactional
    public Payment createPayment(PaymentCreateRequestDto request) {
        log.info("Creating payment: sourceAccount={}, targetAccount={}, amount={}, currency={}",
                request.getSourceAccountNumber(), request.getTargetAccountNumber(),
                request.getAmount(), request.getCurrencyCode());

        Account sourceAccount = accountRepository.findByAccountNumber(request.getSourceAccountNumber())
                .orElseThrow(() -> {
                    log.error("Source account not found: {}", request.getSourceAccountNumber());
                    return new ResourceNotFoundException(
                            "Source account not found: " + request.getSourceAccountNumber());
                });

        Account targetAccount = accountRepository.findByAccountNumber(request.getTargetAccountNumber())
                .orElseThrow(() -> {
                    log.error("Target account not found: {}", request.getTargetAccountNumber());
                    return new ResourceNotFoundException(
                            "Target account not found: " + request.getTargetAccountNumber());
                });

        String requestCurrencyAlfa3 = request.getCurrencyCode().toUpperCase(Locale.ROOT);
        paymentValidator.validatePaymentTransfer(sourceAccount, targetAccount, request.getAmount(), requestCurrencyAlfa3);

        Payment payment = Payment.builder()
                .paymentDate(request.getPaymentDate())
                .sourceAccount(sourceAccount)
                .targetAccount(targetAccount)
                .amount(request.getAmount())
                .currencyCode(requestCurrencyAlfa3)
                .status(PaymentStatus.PENDING)
                .build();

        if (request.getDetails() != null) {
            log.debug("Adding payment details: {} fields", request.getDetails().size());
            for (Map.Entry<String, String> entry : request.getDetails().entrySet()) {
                PaymentDataFieldEnum fieldEnum = PaymentDataFieldEnum.fromJsonFieldName(entry.getKey());
                PaymentDataField field = paymentDataFieldRepository.findById(fieldEnum.getId())
                        .orElseThrow(() -> {
                            log.error("PaymentDataField not found: id={}", fieldEnum.getId());
                            return new BusinessException(
                                    "PaymentDataField with id '" + fieldEnum.getId() + "' not found");
                        });
                PaymentDetail detail = PaymentDetail.builder()
                        .payment(payment)
                        .field(field)
                        .fieldValue(entry.getValue())
                        .build();
                payment.getPaymentDetails().add(detail);
            }
        }

        Payment savedPayment = paymentRepository.save(payment);
        log.info("Payment created successfully: id={}, status={}", savedPayment.getId(), savedPayment.getStatus());
        
        // TODO: Отправить событие в Kafka о создании платежа (payment.created)
        
        return savedPayment;
    }

    @Transactional
    public Payment cancelPayment(Long paymentId) {
        log.info("Cancelling payment: id={}", paymentId);
        try {
            Payment payment = paymentRepository.findByIdWithLock(paymentId)
                    .orElseThrow(() -> {
                        log.error("Payment not found for cancellation: id={}", paymentId);
                        return new ResourceNotFoundException("Payment not found with id: " + paymentId);
                    });

            if (payment.getStatus().isFinalStatus()) {
                log.warn("Cannot cancel payment in final status: id={}, status={}", paymentId, payment.getStatus());
                throw new BusinessException("Cannot cancel payment in final status: " + payment.getStatus());
            }

            payment.setStatus(PaymentStatus.CANCELLED);
            payment.setProcessedAt(ZonedDateTime.now(clock));

            Payment savedPayment = paymentRepository.save(payment);
            log.info("Payment cancelled successfully: id={}", paymentId);
            
            // TODO: Отправить событие в Kafka об отмене платежа (payment.cancelled)
            
            return savedPayment;
        } catch (LockTimeoutException | PessimisticLockException e) {
            log.warn("Payment lock timeout during cancellation: id={}", paymentId, e);
            throw new PaymentLockTimeoutException(
                    "Payment lock timeout for payment id: " + paymentId + ". Payment is locked by another session.", e);
        }
    }

    @Transactional(readOnly = true)
    public Payment getPaymentById(Long id) {
        log.debug("Getting payment by id: {}", id);
        var paymentOptional = paymentRepository.findByIdWithDetails(id);
        return paymentOptional.orElseThrow(() -> {
            log.warn("Payment not found: id={}", id);
            return new ResourceNotFoundException("Payment not found with id: " + id);
        });
    }

    @Transactional(readOnly = true)
    public Page<PaymentSummaryDto> getPaymentsByAccountNumber(String accountNumber, Pageable pageable) {
        if (pageable.getSort().isUnsorted()) {
            pageable = org.springframework.data.domain.PageRequest.of(
                    pageable.getPageNumber(),
                    pageable.getPageSize(),
                    Sort.by("paymentDate").descending());
        }

        return paymentRepository.findByAccountNumber(accountNumber, pageable);
    }

    @Transactional(readOnly = true)
    public Page<PaymentSummaryDto> getPaymentsByClientId(Long clientId, Pageable pageable) {
        if (pageable.getSort().isUnsorted()) {
            pageable = org.springframework.data.domain.PageRequest.of(
                    pageable.getPageNumber(),
                    pageable.getPageSize(),
                    Sort.by("paymentDate").descending());
        }

        return paymentRepository.findByClientId(clientId, pageable);
    }

    @Transactional
    public Payment updatePayment(Long id, PaymentUpdateRequestDto request) {
        log.info("Updating payment: id={}", id);
        try {
            Payment payment = paymentRepository.findByIdWithDetailsAndLock(id)
                    .orElseThrow(() -> {
                        log.error("Payment not found for update: id={}", id);
                        return new ResourceNotFoundException("Payment not found with id: " + id);
                    });

            if (request.getDetails() != null && !request.getDetails().isEmpty()) {
                log.debug("Updating payment details: id={}, fieldsCount={}", id, request.getDetails().size());
                Map<Integer, PaymentDetail> existingDetailsMap = new HashMap<>();
                for (PaymentDetail detail : payment.getPaymentDetails()) {
                    existingDetailsMap.put(detail.getField().getFieldId(), detail);
                }

                for (Map.Entry<String, String> entry : request.getDetails().entrySet()) {
                    PaymentDataFieldEnum fieldEnum = PaymentDataFieldEnum.fromJsonFieldName(entry.getKey());
                    PaymentDataField field = paymentDataFieldRepository.findById(fieldEnum.getId())
                            .orElseThrow(() -> {
                                log.error("PaymentDataField not found: id={}", fieldEnum.getId());
                                return new BusinessException(
                                        "PaymentDataField with id '" + fieldEnum.getId() + "' not found");
                            });

                    PaymentDetail existingDetail = existingDetailsMap.get(field.getFieldId());
                    if (existingDetail == null) {
                        PaymentDetail newDetail = PaymentDetail.builder()
                                .payment(payment)
                                .field(field)
                                .fieldValue(entry.getValue())
                                .build();
                        payment.getPaymentDetails().add(newDetail);
                    }
                }
            }

            Payment savedPayment = paymentRepository.save(payment);
            log.info("Payment updated successfully: id={}", id);
            return savedPayment;
        } catch (LockTimeoutException | PessimisticLockException e) {
            log.warn("Payment lock timeout during update: id={}", id, e);
            throw new PaymentLockTimeoutException(
                    "Payment lock timeout for payment id: " + id + ". Payment is locked by another session.", e);
        }
    }
}
