package ru.backendpro.exception;

public class AccountLockTimeoutException extends RuntimeException {

    public AccountLockTimeoutException(String message) {
        super(message);
    }

    public AccountLockTimeoutException(String message, Throwable cause) {
        super(message, cause);
    }
}

