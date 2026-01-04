package ru.backendpro.dao.repository;

import ru.backendpro.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
    Optional<Currency> findByAlfa3(String alfa3);
}
