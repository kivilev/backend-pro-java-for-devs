package ru.backendpro.dao.repository;

import ru.backendpro.entity.ClientDataField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ClientDataFieldRepository extends JpaRepository<ClientDataField, Integer> {

    Optional<ClientDataField> findByName(String name);
}

