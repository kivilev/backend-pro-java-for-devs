package ru.backendpro.dao.repository;

import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.backendpro.controller.dto.PaymentStatisticsResponseDto;
import ru.backendpro.controller.dto.PaymentSummaryDto;
import ru.backendpro.entity.Payment;
import ru.backendpro.entity.PaymentStatus;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query("""
        SELECT new ru.backendpro.controller.dto.PaymentSummaryDto(
            p.id,
            p.paymentDate,
            sa.accountNumber,
            ta.accountNumber,
            p.amount,
            p.currencyCode,
            p.status,
            null,
            p.processedAt
        )
        FROM Payment p
        LEFT JOIN p.sourceAccount sa
        LEFT JOIN p.targetAccount ta
        WHERE p.sourceAccount.id = :accountId OR p.targetAccount.id = :accountId
        """)
    Page<PaymentSummaryDto> findByAccountId(@Param("accountId") Long accountId, Pageable pageable);

    @Query("""
        SELECT new ru.backendpro.controller.dto.PaymentSummaryDto(
            p.id,
            p.paymentDate,
            sa.accountNumber,
            ta.accountNumber,
            p.amount,
            p.currencyCode,
            p.status,
            null,
            p.processedAt
        )
        FROM Payment p
        LEFT JOIN p.sourceAccount sa
        LEFT JOIN p.targetAccount ta
        WHERE sa.accountNumber = :accountNumber OR ta.accountNumber = :accountNumber
        """)
    Page<PaymentSummaryDto> findByAccountNumber(@Param("accountNumber") String accountNumber, Pageable pageable);

    @Query("""
        SELECT new ru.backendpro.controller.dto.PaymentSummaryDto(
            p.id,
            p.paymentDate,
            sa.accountNumber,
            ta.accountNumber,
            p.amount,
            p.currencyCode,
            p.status,
            null,
            p.processedAt
        )
        FROM Payment p
        LEFT JOIN p.sourceAccount sa
        LEFT JOIN p.targetAccount ta
        LEFT JOIN sa.client sc
        LEFT JOIN ta.client tc
        WHERE sc.id = :clientId OR tc.id = :clientId
        """)
    Page<PaymentSummaryDto> findByClientId(@Param("clientId") Long clientId, Pageable pageable);

    @Query("SELECT p FROM Payment p LEFT JOIN FETCH p.paymentDetails WHERE p.id = :id")
    Optional<Payment> findByIdWithDetails(Long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints(@QueryHint(name = "jakarta.persistence.lock.timeout", value = "2000"))
    @Query("SELECT p FROM Payment p WHERE p.id = :id")
    Optional<Payment> findByIdWithLock(@Param("id") Long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints(@QueryHint(name = "jakarta.persistence.lock.timeout", value = "2000"))
    @Query("SELECT p FROM Payment p LEFT JOIN FETCH p.paymentDetails WHERE p.id = :id")
    Optional<Payment> findByIdWithDetailsAndLock(@Param("id") Long id);

    List<Payment> findByStatus(PaymentStatus status);

    @Query(value = """
        SELECT * FROM payment p 
        WHERE p.status = :status
        ORDER BY p.payment_date ASC 
        LIMIT :limit 
        FOR UPDATE SKIP LOCKED
        """, nativeQuery = true)
    List<Payment> findPaymentsForProcessing(
        @Param("status") int status,
        @Param("limit") int limit
    );

    @Query("""
        SELECT new ru.backendpro.controller.dto.PaymentStatisticsResponseDto(
            COALESCE(SUM(p.amount), 0),
            COUNT(p.id),
            COALESCE(SUM(CASE WHEN p.status = ru.backendpro.entity.PaymentStatus.COMPLETED THEN 1 ELSE 0 END), 0),
            COALESCE(SUM(CASE WHEN p.status = ru.backendpro.entity.PaymentStatus.FAILED THEN 1 ELSE 0 END), 0),
            COALESCE(SUM(CASE WHEN p.status = ru.backendpro.entity.PaymentStatus.CANCELLED THEN 1 ELSE 0 END), 0),
            COALESCE(SUM(CASE WHEN p.status = ru.backendpro.entity.PaymentStatus.PENDING THEN 1 ELSE 0 END), 0),
            COALESCE(AVG(p.amount), 0),
            COALESCE(MIN(p.amount), 0),
            COALESCE(MAX(p.amount), 0),
            :startDate,
            :endDate
        )
        FROM Payment p
        WHERE p.paymentDate >= :startDate AND p.paymentDate <= :endDate
        """)
    PaymentStatisticsResponseDto getPaymentStatistics(ZonedDateTime startDate, ZonedDateTime endDate);
}
