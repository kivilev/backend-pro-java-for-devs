package ru.backendpro.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.backendpro.exception.BusinessException;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("ClientValidationService Unit Tests")
class ClientValidationServiceTest {

    private ClientValidationService clientValidationService;

    @BeforeEach
    void setUp() {
        clientValidationService = new ClientValidationService();
    }

    @Test
    @DisplayName("Should validate client properties successfully when all required properties are present")
    void validateClientProperties_WhenAllRequiredPropertiesPresent_ShouldNotThrowException() {
        // Given
        Map<String, String> properties = new HashMap<>();
        properties.put("firstName", "John");
        properties.put("lastName", "Doe");

        // When & Then
        assertDoesNotThrow(() -> clientValidationService.validateClientProperties(properties));
    }

    @Test
    @DisplayName("Should validate client properties successfully when additional properties are present")
    void validateClientProperties_WhenAdditionalPropertiesPresent_ShouldNotThrowException() {
        // Given
        Map<String, String> properties = new HashMap<>();
        properties.put("firstName", "John");
        properties.put("lastName", "Doe");
        properties.put("middleName", "Michael");
        properties.put("birthday", "1990-01-01");

        // When & Then
        assertDoesNotThrow(() -> clientValidationService.validateClientProperties(properties));
    }

    @Test
    @DisplayName("Should throw exception when properties map is null")
    void validateClientProperties_WhenPropertiesIsNull_ShouldThrowBusinessException() {
        // When & Then
        BusinessException exception = assertThrows(BusinessException.class, () ->
            clientValidationService.validateClientProperties(null)
        );

        assert exception.getMessage().equals("Property 'firstName' is required");
    }

    @Test
    @DisplayName("Should throw exception when firstName property is missing")
    void validateClientProperties_WhenFirstNameMissing_ShouldThrowBusinessException() {
        // Given
        Map<String, String> properties = new HashMap<>();
        properties.put("lastName", "Doe");

        // When & Then
        BusinessException exception = assertThrows(BusinessException.class, () ->
            clientValidationService.validateClientProperties(properties)
        );

        assert exception.getMessage().equals("Property 'firstName' is required");
    }

    @Test
    @DisplayName("Should throw exception when lastName property is missing")
    void validateClientProperties_WhenLastNameMissing_ShouldThrowBusinessException() {
        // Given
        Map<String, String> properties = new HashMap<>();
        properties.put("firstName", "John");

        // When & Then
        BusinessException exception = assertThrows(BusinessException.class, () ->
            clientValidationService.validateClientProperties(properties)
        );

        assert exception.getMessage().equals("Property 'lastName' is required");
    }

    @Test
    @DisplayName("Should throw exception when both firstName and lastName are missing")
    void validateClientProperties_WhenBothRequiredPropertiesMissing_ShouldThrowBusinessException() {
        // Given
        Map<String, String> properties = new HashMap<>();
        properties.put("middleName", "Michael");

        // When & Then
        BusinessException exception = assertThrows(BusinessException.class, () ->
            clientValidationService.validateClientProperties(properties)
        );

        assert exception.getMessage().equals("Property 'firstName' is required");
    }

    @Test
    @DisplayName("Should validate client properties when properties map is empty")
    void validateClientProperties_WhenPropertiesMapIsEmpty_ShouldThrowBusinessException() {
        // Given
        Map<String, String> properties = new HashMap<>();

        // When & Then
        BusinessException exception = assertThrows(BusinessException.class, () ->
            clientValidationService.validateClientProperties(properties)
        );

        assert exception.getMessage().equals("Property 'firstName' is required");
    }

    @Test
    @DisplayName("Should validate client properties when firstName is empty string")
    void validateClientProperties_WhenFirstNameIsEmptyString_ShouldNotThrowException() {
        // Given
        Map<String, String> properties = new HashMap<>();
        properties.put("firstName", "");
        properties.put("lastName", "Doe");

        // When & Then
        assertDoesNotThrow(() -> clientValidationService.validateClientProperties(properties));
    }

    @Test
    @DisplayName("Should validate client properties when lastName is empty string")
    void validateClientProperties_WhenLastNameIsEmptyString_ShouldNotThrowException() {
        // Given
        Map<String, String> properties = new HashMap<>();
        properties.put("firstName", "John");
        properties.put("lastName", "");

        // When & Then
        assertDoesNotThrow(() -> clientValidationService.validateClientProperties(properties));
    }
}

