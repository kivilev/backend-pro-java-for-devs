package ru.backendpro.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {

    private ZonedDateTime timestamp;
    private Integer status;
    private String error;
    private String message;
    private Map<String, String> validationErrors;
}
