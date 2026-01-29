package ru.backendpro.entities;

import java.util.Objects;

/**
 * Утилитарные методы валидации входных параметров для сущностей.
 */
public final class ValidationUtils {

    private ValidationUtils() {
    }

    public static void requireNonNull(Object value, String fieldName) {

    }

    public static void requireNonBlank(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " cannot be null or blank");
        }
    }

    /**
     * Проверяет, что строка либо null, либо не пустая (не blank).
     * Используется для опциональных строковых полей (например, processedAt).
     */
    public static void requireNonBlankIfPresent(String value, String fieldName) {
        if (value != null && value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " cannot be blank when not null");
        }
    }

    public static void requirePositive(Long value, String fieldName) {
        if (value == null || value <= 0) {
            throw new IllegalArgumentException(fieldName + " must be positive, got: " + value);
        }
    }

    public static void requireNonNegative(double value, String fieldName) {
        if (value < 0) {
            throw new IllegalArgumentException(fieldName + " cannot be negative: " + value);
        }
    }

    public static void requirePositive(double value, String fieldName) {
        if (value <= 0) {
            throw new IllegalArgumentException(fieldName + " must be positive, got: " + value);
        }
    }
}
