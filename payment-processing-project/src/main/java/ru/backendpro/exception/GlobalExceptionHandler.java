package ru.backendpro.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Clock;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final Clock clock;

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        log.warn("Resource not found: {}", ex.getMessage());
        ErrorResponse error = ErrorResponse.builder()
                .timestamp(ZonedDateTime.now(clock))
                .status(HttpStatus.NOT_FOUND.value())
                .error("Not Found")
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex) {
        log.warn("Business exception: {}", ex.getMessage());
        ErrorResponse error = ErrorResponse.builder()
                .timestamp(ZonedDateTime.now(clock))
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Business Error")
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccountLockTimeoutException.class)
    public ResponseEntity<ErrorResponse> handleAccountLockTimeoutException(AccountLockTimeoutException ex) {
        log.warn("Account lock timeout: {}", ex.getMessage());
        ErrorResponse error = ErrorResponse.builder()
                .timestamp(ZonedDateTime.now(clock))
                .status(HttpStatus.CONFLICT.value())
                .error("Conflict")
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(PaymentLockTimeoutException.class)
    public ResponseEntity<ErrorResponse> handlePaymentLockTimeoutException(PaymentLockTimeoutException ex) {
        log.warn("Payment lock timeout: {}", ex.getMessage());
        ErrorResponse error = ErrorResponse.builder()
                .timestamp(ZonedDateTime.now(clock))
                .status(HttpStatus.CONFLICT.value())
                .error("Conflict")
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ClientLockTimeoutException.class)
    public ResponseEntity<ErrorResponse> handleClientLockTimeoutException(ClientLockTimeoutException ex) {
        log.warn("Client lock timeout: {}", ex.getMessage());
        ErrorResponse error = ErrorResponse.builder()
                .timestamp(ZonedDateTime.now(clock))
                .status(HttpStatus.CONFLICT.value())
                .error("Conflict")
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        log.warn("Validation failed: {}", errors);
        ErrorResponse error = ErrorResponse.builder()
                .timestamp(ZonedDateTime.now(clock))
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Validation Error")
                .message("Validation failed")
                .validationErrors(errors)
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.warn("Invalid argument: {}", ex.getMessage());
        ErrorResponse error = ErrorResponse.builder()
                .timestamp(ZonedDateTime.now(clock))
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Bad Request")
                .message("Invalid request parameter")
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException ex) {
        log.warn("HTTP method not supported: {}", ex.getMethod());
        ErrorResponse error = ErrorResponse.builder()
                .timestamp(ZonedDateTime.now(clock))
                .status(HttpStatus.METHOD_NOT_ALLOWED.value())
                .error("Method Not Allowed")
                .message("HTTP method is not supported for this endpoint")
                .build();
        return new ResponseEntity<>(error, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleHttpMediaTypeNotSupportedException(
            HttpMediaTypeNotSupportedException ex) {
        log.warn("HTTP media type not supported: {}", ex.getContentType());
        ErrorResponse error = ErrorResponse.builder()
                .timestamp(ZonedDateTime.now(clock))
                .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
                .error("Unsupported Media Type")
                .message("Content type is not supported")
                .build();
        return new ResponseEntity<>(error, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException ex) {
        log.warn("HTTP message not readable: {}", ex.getMessage());
        ErrorResponse error = ErrorResponse.builder()
                .timestamp(ZonedDateTime.now(clock))
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Bad Request")
                .message("Request body is invalid or malformed")
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleMissingServletRequestParameterException(
            MissingServletRequestParameterException ex) {
        log.warn("Missing request parameter: {}", ex.getParameterName());
        ErrorResponse error = ErrorResponse.builder()
                .timestamp(ZonedDateTime.now(clock))
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Bad Request")
                .message("Required request parameter is missing: " + ex.getParameterName())
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex) {
        log.error("Unexpected error occurred", ex);
        ErrorResponse error = ErrorResponse.builder()
                .timestamp(ZonedDateTime.now(clock))
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error("Internal Server Error")
                .message("An unexpected error occurred. Please contact support.")
                .build();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
