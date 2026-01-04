package ru.backendpro.exception;

public class PaymentLockTimeoutException extends RuntimeException {

    public PaymentLockTimeoutException(String message) {
        super(message);
    }

    public PaymentLockTimeoutException(String message, Throwable cause) {
        super(message, cause);
    }
}

