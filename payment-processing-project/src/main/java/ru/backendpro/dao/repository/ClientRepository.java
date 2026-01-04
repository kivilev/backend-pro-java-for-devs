package ru.backendpro.dao.repository;

import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.backendpro.controller.dto.ClientAccountSummaryResponseDto;
import ru.backendpro.entity.Client;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByEmail(String email);

    @Query("SELECT c FROM Client c LEFT JOIN FETCH c.clientData WHERE c.id = :id")
    Optional<Client> findByIdWithData(@Param("id") Long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints(@QueryHint(name = "jakarta.persistence.lock.timeout", value = "2000"))
    @Query("SELECT c FROM Client c WHERE c.id = :id")
    Optional<Client> findByIdWithLock(@Param("id") Long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints(@QueryHint(name = "jakarta.persistence.lock.timeout", value = "2000"))
    @Query("SELECT c FROM Client c LEFT JOIN FETCH c.clientData WHERE c.id = :id")
    Optional<Client> findByIdWithDataAndLock(@Param("id") Long id);

    @Query("""
        SELECT new ru.backendpro.controller.dto.ClientAccountSummaryResponseDto(
            c.id,
            c.email,
            c.phoneNumber,
            c.status,
            c.createdAt,
            COUNT(DISTINCT a.id),
            COALESCE(SUM(a.balance), 0),
            SUM(CASE WHEN a.status = ru.backendpro.entity.AccountStatus.ACTIVE THEN 1 ELSE 0 END)
        )
        FROM Client c
        LEFT JOIN c.accounts a
        GROUP BY c.id, c.email, c.phoneNumber, c.status, c.createdAt
        ORDER BY c.id
        """)
    List<ClientAccountSummaryResponseDto> findAllClientsWithAccountSummary();
}
