package ru.backendpro.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.backendpro.exception.BusinessException;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientValidationService {

    public void validateClientProperties(Map<String, String> properties) {
        log.debug("Validating client properties");
        if (properties == null || !properties.containsKey("firstName")) {
            log.warn("Validation failed: Property 'firstName' is required");
            throw new BusinessException("Property 'firstName' is required");
        }
        if (!properties.containsKey("lastName")) {
            log.warn("Validation failed: Property 'lastName' is required");
            throw new BusinessException("Property 'lastName' is required");
        }
        log.debug("Client properties validation passed");
    }
}

