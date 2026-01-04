package ru.backendpro.controller;

import ru.backendpro.controller.mapper.PaymentMapper;
import ru.backendpro.controller.dto.PaymentCreateRequestDto;
import ru.backendpro.controller.dto.PaymentResponseDto;
import ru.backendpro.controller.dto.PaymentUpdateRequestDto;
import ru.backendpro.entity.Payment;
import ru.backendpro.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
@Tag(name = "Payment", description = "Payment management APIs")
public class PaymentController {

    private final PaymentService paymentService;
    private final PaymentMapper paymentMapper;

    @PostMapping
    @Operation(summary = "Create a new payment")
    public ResponseEntity<PaymentResponseDto> createPayment(@Valid @RequestBody PaymentCreateRequestDto request) {
        log.info("POST /api/v1/payments - Creating payment: sourceAccount={}, targetAccount={}, amount={}",
                request.getSourceAccountNumber(), request.getTargetAccountNumber(), request.getAmount());
        Payment payment = paymentService.createPayment(request);
        PaymentResponseDto response = paymentMapper.mapToResponse(payment);
        log.info("Payment created via API: id={}", payment.getId());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/cancel")
    @Operation(summary = "Cancel payment")
    public PaymentResponseDto cancelPayment(@PathVariable Long id) {
        log.info("POST /api/v1/payments/{}/cancel - Cancelling payment", id);
        Payment payment = paymentService.cancelPayment(id);
        log.info("Payment cancelled via API: id={}", id);
        return paymentMapper.mapToResponse(payment);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get payment by ID")
    public PaymentResponseDto getPaymentById(@PathVariable Long id) {
        log.debug("GET /api/v1/payments/{} - Getting payment by id", id);
        Payment payment = paymentService.getPaymentById(id);
        return paymentMapper.mapToResponse(payment);
    }

    @GetMapping("/account/{accountNumber}")
    @Operation(summary = "Get payments by account number")
    public Page<PaymentResponseDto> getPaymentsByAccountNumber(
            @PathVariable String accountNumber,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "paymentDate") String sortBy,
            @RequestParam(defaultValue = "DESC") Sort.Direction direction) {
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        return paymentService.getPaymentsByAccountNumber(accountNumber, pageable)
                .map(paymentMapper::mapToResponse);
    }

    @GetMapping("/client/{clientId}")
    @Operation(summary = "Get payments by client ID")
    public Page<PaymentResponseDto> getPaymentsByClientId(
            @PathVariable Long clientId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "paymentDate") String sortBy,
            @RequestParam(defaultValue = "DESC") Sort.Direction direction) {
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        return paymentService.getPaymentsByClientId(clientId, pageable)
                .map(paymentMapper::mapToResponse);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update payment properties")
    public PaymentResponseDto updatePayment(
            @PathVariable Long id,
            @Valid @RequestBody PaymentUpdateRequestDto request) {
        log.info("PATCH /api/v1/payments/{} - Updating payment", id);
        Payment payment = paymentService.updatePayment(id, request);
        log.info("Payment updated via API: id={}", id);
        return paymentMapper.mapToResponse(payment);
    }
}
