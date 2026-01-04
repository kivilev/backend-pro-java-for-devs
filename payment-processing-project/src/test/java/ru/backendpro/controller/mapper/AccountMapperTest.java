package ru.backendpro.controller.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.backendpro.controller.dto.AccountResponseDto;
import ru.backendpro.entity.Account;
import ru.backendpro.entity.AccountStatus;
import ru.backendpro.entity.Client;
import ru.backendpro.entity.Currency;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("AccountMapper Unit Tests")
class AccountMapperTest {

    private AccountMapper accountMapper;
    private Currency usdCurrency;
    private Client client;

    @BeforeEach
    void setUp() {
        accountMapper = new AccountMapper();
        
        usdCurrency = Currency.builder()
                .currencyId(1)
                .alfa3("USD")
                .description("US Dollar")
                .build();

        client = Client.builder()
                .id(1L)
                .email("client@example.com")
                .build();
    }

    @Test
    @DisplayName("Should map Account to AccountResponseDto successfully with all fields")
    void mapToResponse_WhenAccountWithAllFields_ShouldReturnCorrectDto() {
        // Given
        Account account = Account.builder()
                .id(1L)
                .client(client)
                .accountNumber("ACC-001")
                .currency(usdCurrency)
                .balance(new BigDecimal("1000.00"))
                .status(AccountStatus.ACTIVE)
                .build();

        // When
        AccountResponseDto result = accountMapper.mapToResponse(account);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(1L, result.getClientId());
        assertEquals("ACC-001", result.getAccountNumber());
        assertEquals("USD", result.getCurrencyCode());
        assertEquals(new BigDecimal("1000.00"), result.getBalance());
        assertEquals("ACTIVE", result.getStatus());
    }

    @Test
    @DisplayName("Should map Account to AccountResponseDto when status is BLOCKED")
    void mapToResponse_WhenAccountStatusIsBlocked_ShouldReturnBlockedStatus() {
        // Given
        Account account = Account.builder()
                .id(2L)
                .client(client)
                .accountNumber("ACC-002")
                .currency(usdCurrency)
                .balance(new BigDecimal("500.00"))
                .status(AccountStatus.BLOCKED)
                .build();

        // When
        AccountResponseDto result = accountMapper.mapToResponse(account);

        // Then
        assertNotNull(result);
        assertEquals(2L, result.getId());
        assertEquals("BLOCKED", result.getStatus());
    }

    @Test
    @DisplayName("Should map Account to AccountResponseDto when status is null")
    void mapToResponse_WhenAccountStatusIsNull_ShouldReturnNullStatus() {
        // Given
        Account account = Account.builder()
                .id(3L)
                .client(client)
                .accountNumber("ACC-003")
                .currency(usdCurrency)
                .balance(new BigDecimal("200.00"))
                .status(null)
                .build();

        // When
        AccountResponseDto result = accountMapper.mapToResponse(account);

        // Then
        assertNotNull(result);
        assertEquals(3L, result.getId());
        assertNull(result.getStatus());
    }

    @Test
    @DisplayName("Should map Account to AccountResponseDto with zero balance")
    void mapToResponse_WhenAccountBalanceIsZero_ShouldReturnZeroBalance() {
        // Given
        Account account = Account.builder()
                .id(4L)
                .client(client)
                .accountNumber("ACC-004")
                .currency(usdCurrency)
                .balance(BigDecimal.ZERO)
                .status(AccountStatus.ACTIVE)
                .build();

        // When
        AccountResponseDto result = accountMapper.mapToResponse(account);

        // Then
        assertNotNull(result);
        assertEquals(4L, result.getId());
        assertEquals(BigDecimal.ZERO, result.getBalance());
    }

    @Test
    @DisplayName("Should map Account to AccountResponseDto with negative balance")
    void mapToResponse_WhenAccountBalanceIsNegative_ShouldReturnNegativeBalance() {
        // Given
        Account account = Account.builder()
                .id(5L)
                .client(client)
                .accountNumber("ACC-005")
                .currency(usdCurrency)
                .balance(new BigDecimal("-100.00"))
                .status(AccountStatus.ACTIVE)
                .build();

        // When
        AccountResponseDto result = accountMapper.mapToResponse(account);

        // Then
        assertNotNull(result);
        assertEquals(5L, result.getId());
        assertEquals(new BigDecimal("-100.00"), result.getBalance());
    }

    @Test
    @DisplayName("Should map Account to AccountResponseDto with different currency")
    void mapToResponse_WhenAccountWithDifferentCurrency_ShouldReturnCorrectCurrencyCode() {
        // Given
        Currency eurCurrency = Currency.builder()
                .currencyId(2)
                .alfa3("EUR")
                .description("Euro")
                .build();

        Account account = Account.builder()
                .id(6L)
                .client(client)
                .accountNumber("ACC-006")
                .currency(eurCurrency)
                .balance(new BigDecimal("1500.00"))
                .status(AccountStatus.ACTIVE)
                .build();

        // When
        AccountResponseDto result = accountMapper.mapToResponse(account);

        // Then
        assertNotNull(result);
        assertEquals(6L, result.getId());
        assertEquals("EUR", result.getCurrencyCode());
    }

    @Test
    @DisplayName("Should map Account to AccountResponseDto with large balance")
    void mapToResponse_WhenAccountWithLargeBalance_ShouldReturnCorrectBalance() {
        // Given
        Account account = Account.builder()
                .id(7L)
                .client(client)
                .accountNumber("ACC-007")
                .currency(usdCurrency)
                .balance(new BigDecimal("999999999.99"))
                .status(AccountStatus.ACTIVE)
                .build();

        // When
        AccountResponseDto result = accountMapper.mapToResponse(account);

        // Then
        assertNotNull(result);
        assertEquals(7L, result.getId());
        assertEquals(new BigDecimal("999999999.99"), result.getBalance());
    }
}

