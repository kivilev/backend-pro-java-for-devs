package ru.backendpro.exception;

public class ClientLockTimeoutException extends RuntimeException {

    public ClientLockTimeoutException(String message) {
        super(message);
    }

    public ClientLockTimeoutException(String message, Throwable cause) {
        super(message, cause);
    }
}

