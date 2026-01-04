package ru.backendpro.dao.repository;

import ru.backendpro.entity.PaymentDataField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PaymentDataFieldRepository extends JpaRepository<PaymentDataField, Integer> {

    Optional<PaymentDataField> findByName(String name);
}

