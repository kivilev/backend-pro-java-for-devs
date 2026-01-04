package ru.backendpro.dao.repository;

import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.backendpro.entity.Account;
import ru.backendpro.entity.AccountStatus;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByAccountNumber(String accountNumber);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints(@QueryHint(name = "jakarta.persistence.lock.timeout", value = "2000"))
    @Query("SELECT a FROM Account a WHERE a.accountNumber = :accountNumber")
    Optional<Account> findByAccountNumberWithLock(@Param("accountNumber") String accountNumber);

    List<Account> findByClientId(Long clientId);

    @Query("SELECT a FROM Account a WHERE a.client.id = :clientId AND a.currency.alfa3 = :currencyAlfa3")
    Optional<Account> findByClientIdAndCurrencyAlfa3(Long clientId, String currencyAlfa3);

    List<Account> findByStatus(AccountStatus status);
}
