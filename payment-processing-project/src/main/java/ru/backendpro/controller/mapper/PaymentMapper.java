package ru.backendpro.controller.mapper;

import org.springframework.stereotype.Component;
import ru.backendpro.controller.dto.PaymentResponseDto;
import ru.backendpro.controller.dto.PaymentSummaryDto;
import ru.backendpro.entity.Payment;
import ru.backendpro.entity.PaymentDetail;
import ru.backendpro.entity.PaymentDataFieldEnum;
import ru.backendpro.entity.PaymentStatus;

import java.util.HashMap;
import java.util.Map;

@Component
public class PaymentMapper {

    public PaymentResponseDto mapToResponse(Payment payment) {
        Map<String, String> details = new HashMap<>();
        for (PaymentDetail detail : payment.getPaymentDetails()) {
            PaymentDataFieldEnum fieldEnum = PaymentDataFieldEnum.fromId(detail.getField().getFieldId());
            details.put(fieldEnum.getJsonFieldName(), detail.getFieldValue());
        }

        return PaymentResponseDto.builder()
                .id(payment.getId())
                .paymentDate(payment.getPaymentDate())
                .sourceAccountNumber(payment.getSourceAccount().getAccountNumber())
                .targetAccountNumber(payment.getTargetAccount().getAccountNumber())
                .amount(payment.getAmount())
                .currencyCode(payment.getCurrencyCode())
                .status(payment.getStatus())
                .statusDescription(getStatusDescription(payment.getStatus()))
                .details(details)
                .processedAt(payment.getProcessedAt())
                .build();
    }

    public PaymentResponseDto mapToResponse(PaymentSummaryDto summary) {
        String statusDescription = summary.getStatusDescription();
        if (statusDescription == null || statusDescription.isEmpty()) {
            statusDescription = getStatusDescription(summary.getStatus());
        }

        return PaymentResponseDto.builder()
                .id(summary.getId())
                .paymentDate(summary.getPaymentDate())
                .sourceAccountNumber(summary.getSourceAccountNumber())
                .targetAccountNumber(summary.getTargetAccountNumber())
                .amount(summary.getAmount())
                .currencyCode(summary.getCurrencyCode())
                .status(summary.getStatus())
                .statusDescription(statusDescription)
                .details(null)
                .processedAt(summary.getProcessedAt())
                .build();
    }

    private String getStatusDescription(PaymentStatus status) {
        if (status == null) {
            return "UNKNOWN";
        }
        return status.name();
    }
}

