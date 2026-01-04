package ru.backendpro.controller.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.backendpro.controller.dto.PaymentResponseDto;
import ru.backendpro.controller.dto.PaymentSummaryDto;
import ru.backendpro.entity.Account;
import ru.backendpro.entity.Currency;
import ru.backendpro.entity.Payment;
import ru.backendpro.entity.PaymentDataField;
import ru.backendpro.entity.PaymentDataFieldEnum;
import ru.backendpro.entity.PaymentDetail;
import ru.backendpro.entity.PaymentStatus;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("PaymentMapper Unit Tests")
class PaymentMapperTest {

    private PaymentMapper paymentMapper;
    private Currency usdCurrency;
    private Account sourceAccount;
    private Account targetAccount;

    @BeforeEach
    void setUp() {
        paymentMapper = new PaymentMapper();
        
        usdCurrency = Currency.builder()
                .currencyId(1)
                .alfa3("USD")
                .description("US Dollar")
                .build();

        sourceAccount = Account.builder()
                .id(1L)
                .accountNumber("ACC-001")
                .currency(usdCurrency)
                .build();

        targetAccount = Account.builder()
                .id(2L)
                .accountNumber("ACC-002")
                .currency(usdCurrency)
                .build();
    }

    @Test
    @DisplayName("Should map Payment to PaymentResponseDto successfully with all fields")
    void mapToResponse_WhenPaymentWithAllFields_ShouldReturnCorrectDto() {
        // Given
        ZonedDateTime paymentDate = ZonedDateTime.now();
        ZonedDateTime processedAt = ZonedDateTime.now().plusMinutes(5);
        
        PaymentDataField clientSoftwareField = PaymentDataField.builder()
                .fieldId(PaymentDataFieldEnum.CLIENT_SOFTWARE.getId())
                .name("Client Software")
                .description("Client software name")
                .build();
        
        PaymentDataField ipField = PaymentDataField.builder()
                .fieldId(PaymentDataFieldEnum.IP.getId())
                .name("IP")
                .description("IP address")
                .build();

        PaymentDetail detail1 = PaymentDetail.builder()
                .field(clientSoftwareField)
                .fieldValue("Mobile App v1.0")
                .build();

        PaymentDetail detail2 = PaymentDetail.builder()
                .field(ipField)
                .fieldValue("192.168.1.1")
                .build();

        List<PaymentDetail> paymentDetails = new ArrayList<>();
        paymentDetails.add(detail1);
        paymentDetails.add(detail2);

        Payment payment = Payment.builder()
                .id(1L)
                .paymentDate(paymentDate)
                .sourceAccount(sourceAccount)
                .targetAccount(targetAccount)
                .amount(new BigDecimal("500.00"))
                .currencyCode("USD")
                .status(PaymentStatus.COMPLETED)
                .processedAt(processedAt)
                .paymentDetails(paymentDetails)
                .build();

        // When
        PaymentResponseDto result = paymentMapper.mapToResponse(payment);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(paymentDate, result.getPaymentDate());
        assertEquals("ACC-001", result.getSourceAccountNumber());
        assertEquals("ACC-002", result.getTargetAccountNumber());
        assertEquals(new BigDecimal("500.00"), result.getAmount());
        assertEquals("USD", result.getCurrencyCode());
        assertEquals(PaymentStatus.COMPLETED, result.getStatus());
        assertEquals("COMPLETED", result.getStatusDescription());
        assertEquals(processedAt, result.getProcessedAt());
        assertNotNull(result.getDetails());
        assertEquals(2, result.getDetails().size());
        assertEquals("Mobile App v1.0", result.getDetails().get("clientSoftware"));
        assertEquals("192.168.1.1", result.getDetails().get("ip"));
    }

    @Test
    @DisplayName("Should map Payment to PaymentResponseDto when payment has no details")
    void mapToResponse_WhenPaymentWithoutDetails_ShouldReturnDtoWithEmptyDetails() {
        // Given
        ZonedDateTime paymentDate = ZonedDateTime.now();
        
        Payment payment = Payment.builder()
                .id(2L)
                .paymentDate(paymentDate)
                .sourceAccount(sourceAccount)
                .targetAccount(targetAccount)
                .amount(new BigDecimal("100.00"))
                .currencyCode("USD")
                .status(PaymentStatus.PENDING)
                .processedAt(null)
                .paymentDetails(new ArrayList<>())
                .build();

        // When
        PaymentResponseDto result = paymentMapper.mapToResponse(payment);

        // Then
        assertNotNull(result);
        assertEquals(2L, result.getId());
        assertEquals("PENDING", result.getStatusDescription());
        assertNotNull(result.getDetails());
        assertEquals(0, result.getDetails().size());
    }

    @Test
    @DisplayName("Should map Payment to PaymentResponseDto when status is null")
    void mapToResponse_WhenPaymentStatusIsNull_ShouldReturnDtoWithUnknownStatus() {
        // Given
        Payment payment = Payment.builder()
                .id(3L)
                .paymentDate(ZonedDateTime.now())
                .sourceAccount(sourceAccount)
                .targetAccount(targetAccount)
                .amount(new BigDecimal("200.00"))
                .currencyCode("USD")
                .status(null)
                .paymentDetails(new ArrayList<>())
                .build();

        // When
        PaymentResponseDto result = paymentMapper.mapToResponse(payment);

        // Then
        assertNotNull(result);
        assertNull(result.getStatus());
        assertEquals("UNKNOWN", result.getStatusDescription());
    }

    @Test
    @DisplayName("Should map PaymentSummaryDto to PaymentResponseDto successfully")
    void mapToResponse_WhenPaymentSummaryDto_ShouldReturnCorrectDto() {
        // Given
        ZonedDateTime paymentDate = ZonedDateTime.now();
        ZonedDateTime processedAt = ZonedDateTime.now().plusMinutes(5);
        
        PaymentSummaryDto summary = PaymentSummaryDto.builder()
                .id(1L)
                .paymentDate(paymentDate)
                .sourceAccountNumber("ACC-001")
                .targetAccountNumber("ACC-002")
                .amount(new BigDecimal("500.00"))
                .currencyCode("USD")
                .status(PaymentStatus.COMPLETED)
                .statusDescription("Payment completed successfully")
                .processedAt(processedAt)
                .build();

        // When
        PaymentResponseDto result = paymentMapper.mapToResponse(summary);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(paymentDate, result.getPaymentDate());
        assertEquals("ACC-001", result.getSourceAccountNumber());
        assertEquals("ACC-002", result.getTargetAccountNumber());
        assertEquals(new BigDecimal("500.00"), result.getAmount());
        assertEquals("USD", result.getCurrencyCode());
        assertEquals(PaymentStatus.COMPLETED, result.getStatus());
        assertEquals("Payment completed successfully", result.getStatusDescription());
        assertEquals(processedAt, result.getProcessedAt());
        assertNull(result.getDetails());
    }

    @Test
    @DisplayName("Should map PaymentSummaryDto to PaymentResponseDto when statusDescription is null")
    void mapToResponse_WhenPaymentSummaryDtoWithNullStatusDescription_ShouldUseStatusName() {
        // Given
        PaymentSummaryDto summary = PaymentSummaryDto.builder()
                .id(2L)
                .paymentDate(ZonedDateTime.now())
                .sourceAccountNumber("ACC-001")
                .targetAccountNumber("ACC-002")
                .amount(new BigDecimal("100.00"))
                .currencyCode("USD")
                .status(PaymentStatus.FAILED)
                .statusDescription(null)
                .build();

        // When
        PaymentResponseDto result = paymentMapper.mapToResponse(summary);

        // Then
        assertNotNull(result);
        assertEquals(PaymentStatus.FAILED, result.getStatus());
        assertEquals("FAILED", result.getStatusDescription());
    }

    @Test
    @DisplayName("Should map PaymentSummaryDto to PaymentResponseDto when statusDescription is empty")
    void mapToResponse_WhenPaymentSummaryDtoWithEmptyStatusDescription_ShouldUseStatusName() {
        // Given
        PaymentSummaryDto summary = PaymentSummaryDto.builder()
                .id(3L)
                .paymentDate(ZonedDateTime.now())
                .sourceAccountNumber("ACC-001")
                .targetAccountNumber("ACC-002")
                .amount(new BigDecimal("200.00"))
                .currencyCode("USD")
                .status(PaymentStatus.CANCELLED)
                .statusDescription("")
                .build();

        // When
        PaymentResponseDto result = paymentMapper.mapToResponse(summary);

        // Then
        assertNotNull(result);
        assertEquals(PaymentStatus.CANCELLED, result.getStatus());
        assertEquals("CANCELLED", result.getStatusDescription());
    }

    @Test
    @DisplayName("Should map PaymentSummaryDto to PaymentResponseDto when status is null")
    void mapToResponse_WhenPaymentSummaryDtoWithNullStatus_ShouldReturnUnknownStatus() {
        // Given
        PaymentSummaryDto summary = PaymentSummaryDto.builder()
                .id(4L)
                .paymentDate(ZonedDateTime.now())
                .sourceAccountNumber("ACC-001")
                .targetAccountNumber("ACC-002")
                .amount(new BigDecimal("300.00"))
                .currencyCode("USD")
                .status(null)
                .statusDescription(null)
                .build();

        // When
        PaymentResponseDto result = paymentMapper.mapToResponse(summary);

        // Then
        assertNotNull(result);
        assertNull(result.getStatus());
        assertEquals("UNKNOWN", result.getStatusDescription());
    }

    @Test
    @DisplayName("Should map Payment with all payment detail types")
    void mapToResponse_WhenPaymentWithAllDetailTypes_ShouldMapAllDetails() {
        // Given
        PaymentDataField clientSoftwareField = PaymentDataField.builder()
                .fieldId(PaymentDataFieldEnum.CLIENT_SOFTWARE.getId())
                .build();
        PaymentDataField ipField = PaymentDataField.builder()
                .fieldId(PaymentDataFieldEnum.IP.getId())
                .build();
        PaymentDataField noteField = PaymentDataField.builder()
                .fieldId(PaymentDataFieldEnum.NOTE.getId())
                .build();
        PaymentDataField isCheckedFraudField = PaymentDataField.builder()
                .fieldId(PaymentDataFieldEnum.IS_CHECKED_FRAUD.getId())
                .build();

        List<PaymentDetail> paymentDetails = new ArrayList<>();
        paymentDetails.add(PaymentDetail.builder().field(clientSoftwareField).fieldValue("App v1.0").build());
        paymentDetails.add(PaymentDetail.builder().field(ipField).fieldValue("10.0.0.1").build());
        paymentDetails.add(PaymentDetail.builder().field(noteField).fieldValue("Test payment").build());
        paymentDetails.add(PaymentDetail.builder().field(isCheckedFraudField).fieldValue("true").build());

        Payment payment = Payment.builder()
                .id(5L)
                .paymentDate(ZonedDateTime.now())
                .sourceAccount(sourceAccount)
                .targetAccount(targetAccount)
                .amount(new BigDecimal("400.00"))
                .currencyCode("USD")
                .status(PaymentStatus.COMPLETED)
                .paymentDetails(paymentDetails)
                .build();

        // When
        PaymentResponseDto result = paymentMapper.mapToResponse(payment);

        // Then
        assertNotNull(result);
        assertNotNull(result.getDetails());
        assertEquals(4, result.getDetails().size());
        assertEquals("App v1.0", result.getDetails().get("clientSoftware"));
        assertEquals("10.0.0.1", result.getDetails().get("ip"));
        assertEquals("Test payment", result.getDetails().get("note"));
        assertEquals("true", result.getDetails().get("isCheckedFraud"));
    }
}

