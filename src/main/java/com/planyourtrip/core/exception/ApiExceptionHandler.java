package com.planyourtrip.core.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.EntityNotFoundException;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Slf4j
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<BackendApiResponse> handleEntityNotFound(EntityNotFoundException e) {
        log.error(e.getMessage(), e);
        return buildResponse(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<BackendApiResponse> handleNoSuchElement(NoSuchElementException e) {
        log.error(e.getMessage(), e);
        return buildResponse(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<BackendApiResponse> handleBusinessException(BusinessException e) {
        if (e.getResponseCode().equals(ResponseCode.ENTITY_NOT_FOUND)) {
            log.error(e.getMessage(), e);
            return buildResponse(HttpStatus.NOT_FOUND, e.getMessage());
        } else {
            return handleGeneral(e);
        }
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<BackendApiResponse> handleBindException(BindException e) {
        log.error(e.getMessage(), e);
        var errors = getErrorsFromBindingResult(e);
        return ResponseEntity.badRequest()
                .body(BackendApiResponse.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message(e.getMessage())
                        .errors(errors)
                        .build());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<BackendApiResponse> handleGeneral(Exception e) {
        log.error(e.getMessage(), e);
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Внутренняя ошибка сервера");
    }

    @Override
    @Nonnull
    protected ResponseEntity<Object> handleExceptionInternal(@Nonnull Exception e,
                                                             @Nullable Object body,
                                                             @Nonnull HttpHeaders headers,
                                                             @Nonnull HttpStatusCode statusCode,
                                                             @Nonnull WebRequest request) {
        log.error(e.getMessage(), e);
        return ResponseEntity
                .status(statusCode)
                .headers(headers)
                .body(BackendApiResponse.builder()
                        .status(statusCode.value())
                        .message(e.getMessage()));
    }

    private ResponseEntity<BackendApiResponse> buildResponse(HttpStatus status, String message) {
        return ResponseEntity.status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BackendApiResponse.builder()
                        .status(status.value())
                        .message(message)
                        .build());
    }

    private Map<String, String> getErrorsFromBindingResult(BindException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult()
                .getFieldErrors()
                .forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));
        return errors;
    }

    @Builder
    @Schema(description = "Контейнер для ответов на запросы")
    @Data
    public static class BackendApiResponse implements Serializable {
        private int status;
        private String message;
        private Object errors;
    }
}