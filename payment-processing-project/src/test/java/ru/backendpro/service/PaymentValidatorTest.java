package ru.backendpro.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.backendpro.entity.Account;
import ru.backendpro.entity.AccountStatus;
import ru.backendpro.entity.Currency;
import ru.backendpro.exception.BusinessException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("PaymentValidator Unit Tests")
class PaymentValidatorTest {

    private PaymentValidator paymentValidator;
    private Account sourceAccount;
    private Account targetAccount;
    private Currency usdCurrency;
    private Currency eurCurrency;

    @BeforeEach
    void setUp() {
        paymentValidator = new PaymentValidator();
        
        usdCurrency = Currency.builder()
                .currencyId(1)
                .alfa3("USD")
                .description("US Dollar")
                .build();
        
        eurCurrency = Currency.builder()
                .currencyId(2)
                .alfa3("EUR")
                .description("Euro")
                .build();

        sourceAccount = Account.builder()
                .id(1L)
                .accountNumber("ACC-001")
                .currency(usdCurrency)
                .balance(new BigDecimal("1000.00"))
                .status(AccountStatus.ACTIVE)
                .build();

        targetAccount = Account.builder()
                .id(2L)
                .accountNumber("ACC-002")
                .currency(usdCurrency)
                .balance(new BigDecimal("500.00"))
                .status(AccountStatus.ACTIVE)
                .build();
    }

    @Test
    @DisplayName("Should validate payment transfer successfully when all conditions are met")
    void validatePaymentTransfer_WhenAllConditionsMet_ShouldNotThrowException() {
        // Given
        BigDecimal amount = new BigDecimal("100.00");
        String currencyCode = "USD";

        // When & Then
        assertDoesNotThrow(() -> 
            paymentValidator.validatePaymentTransfer(sourceAccount, targetAccount, amount, currencyCode)
        );
    }

    @Test
    @DisplayName("Should throw exception when source and target accounts are the same")
    void validatePaymentTransfer_WhenSameAccount_ShouldThrowBusinessException() {
        // Given
        Account sameAccount = Account.builder()
                .id(1L)
                .accountNumber("ACC-001")
                .currency(usdCurrency)
                .balance(new BigDecimal("1000.00"))
                .status(AccountStatus.ACTIVE)
                .build();
        BigDecimal amount = new BigDecimal("100.00");
        String currencyCode = "USD";

        // When & Then
        BusinessException exception = assertThrows(BusinessException.class, () ->
            paymentValidator.validatePaymentTransfer(sameAccount, sameAccount, amount, currencyCode)
        );

        assert exception.getMessage().equals("Cannot transfer to the same account");
    }

    @Test
    @DisplayName("Should throw exception when source account is not active")
    void validatePaymentTransfer_WhenSourceAccountNotActive_ShouldThrowBusinessException() {
        // Given
        Account blockedSourceAccount = Account.builder()
                .id(1L)
                .accountNumber("ACC-001")
                .currency(usdCurrency)
                .balance(new BigDecimal("1000.00"))
                .status(AccountStatus.BLOCKED)
                .build();
        BigDecimal amount = new BigDecimal("100.00");
        String currencyCode = "USD";

        // When & Then
        BusinessException exception = assertThrows(BusinessException.class, () ->
            paymentValidator.validatePaymentTransfer(blockedSourceAccount, targetAccount, amount, currencyCode)
        );

        assert exception.getMessage().equals("Source account is not active");
    }

    @Test
    @DisplayName("Should throw exception when target account is not active")
    void validatePaymentTransfer_WhenTargetAccountNotActive_ShouldThrowBusinessException() {
        // Given
        Account blockedTargetAccount = Account.builder()
                .id(2L)
                .accountNumber("ACC-002")
                .currency(usdCurrency)
                .balance(new BigDecimal("500.00"))
                .status(AccountStatus.BLOCKED)
                .build();
        BigDecimal amount = new BigDecimal("100.00");
        String currencyCode = "USD";

        // When & Then
        BusinessException exception = assertThrows(BusinessException.class, () ->
            paymentValidator.validatePaymentTransfer(sourceAccount, blockedTargetAccount, amount, currencyCode)
        );

        assert exception.getMessage().equals("Target account is not active");
    }

    @Test
    @DisplayName("Should throw exception when source account has insufficient funds")
    void validatePaymentTransfer_WhenInsufficientFunds_ShouldThrowBusinessException() {
        // Given
        BigDecimal amount = new BigDecimal("1500.00");
        String currencyCode = "USD";

        // When & Then
        BusinessException exception = assertThrows(BusinessException.class, () ->
            paymentValidator.validatePaymentTransfer(sourceAccount, targetAccount, amount, currencyCode)
        );

        assert exception.getMessage().equals("Insufficient funds");
    }

    @Test
    @DisplayName("Should throw exception when amount equals balance")
    void validatePaymentTransfer_WhenAmountEqualsBalance_ShouldNotThrowException() {
        // Given
        BigDecimal amount = new BigDecimal("1000.00");
        String currencyCode = "USD";

        // When & Then
        assertDoesNotThrow(() ->
            paymentValidator.validatePaymentTransfer(sourceAccount, targetAccount, amount, currencyCode)
        );
    }

    @Test
    @DisplayName("Should throw exception when source account currency does not match payment currency")
    void validatePaymentTransfer_WhenSourceAccountCurrencyMismatch_ShouldThrowBusinessException() {
        // Given
        BigDecimal amount = new BigDecimal("100.00");
        String currencyCode = "EUR";

        // When & Then
        BusinessException exception = assertThrows(BusinessException.class, () ->
            paymentValidator.validatePaymentTransfer(sourceAccount, targetAccount, amount, currencyCode)
        );

        assert exception.getMessage().equals("Source account currency does not match payment currency");
    }

    @Test
    @DisplayName("Should throw exception when target account currency does not match payment currency")
    void validatePaymentTransfer_WhenTargetAccountCurrencyMismatch_ShouldThrowBusinessException() {
        // Given
        Account eurTargetAccount = Account.builder()
                .id(2L)
                .accountNumber("ACC-002")
                .currency(eurCurrency)
                .balance(new BigDecimal("500.00"))
                .status(AccountStatus.ACTIVE)
                .build();
        BigDecimal amount = new BigDecimal("100.00");
        String currencyCode = "USD";

        // When & Then
        BusinessException exception = assertThrows(BusinessException.class, () ->
            paymentValidator.validatePaymentTransfer(sourceAccount, eurTargetAccount, amount, currencyCode)
        );

        assert exception.getMessage().equals("Target account currency does not match payment currency");
    }

    @Test
    @DisplayName("Should validate payment transfer with zero amount")
    void validatePaymentTransfer_WhenZeroAmount_ShouldNotThrowException() {
        // Given
        BigDecimal amount = BigDecimal.ZERO;
        String currencyCode = "USD";

        // When & Then
        assertDoesNotThrow(() ->
            paymentValidator.validatePaymentTransfer(sourceAccount, targetAccount, amount, currencyCode)
        );
    }
}

