package org.example.exchangeratewebapiproject.exceptionHandler;

import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.nio.file.AccessDeniedException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String ARGUMENT_VALIDATION_FAILED = "Argument validation failed";

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public final ResponseEntity<Map<String, Object>> handle(AccessDeniedException ex, WebRequest request) {
        return ofType(request, HttpStatus.FORBIDDEN, "You do not have permission to access this functionality");
    }

    @ExceptionHandler(PSQLException.class)
    public final ResponseEntity<Map<String, Object>> handle(PSQLException ex, WebRequest request) {
        return ofType(request, HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public final ResponseEntity<Map<String, Object>> handle(AlreadyExistsException ex, WebRequest request) {
        return ofType(request, HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<Map<String, Object>> handle(NotFoundException ex, WebRequest request) {
        return ofType(request, HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<Map<String, Object>> handle(IllegalArgumentException ex, WebRequest request) {
        return ofType(request, HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(CustomRestClientException.class)
    public final ResponseEntity<Map<String, Object>> handle(CustomRestClientException ex, WebRequest request) {
        return ofType(request, HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    @ExceptionHandler(RefreshTokenException.class)
    public final ResponseEntity<Map<String, Object>> handle(RefreshTokenException ex, WebRequest request) {
        return ofType(request, HttpStatus.UNAUTHORIZED, ex.getMessage());
    }

    @ExceptionHandler(JwtTokenInvalidException.class)
    public final ResponseEntity<Map<String, Object>> handle(JwtTokenInvalidException ex, WebRequest request) {
        return ofType(request, HttpStatus.UNAUTHORIZED, ex.getMessage());
    }

    @ExceptionHandler(JwtException.class)
    public final ResponseEntity<Map<String, Object>> handle(JwtException ex, WebRequest request) {
        return ofType(request, HttpStatus.UNAUTHORIZED, ex.getMessage());
    }

    private ResponseEntity<Map<String, Object>> ofType(WebRequest request, HttpStatus status, String message) {
        return ofType(request, status, message, Collections.emptyList(), 0);
    }



    private ResponseEntity<Map<String, Object>> ofType(
            WebRequest request, HttpStatus status, String message, List<Object> validationErrors, int errorCode) {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("status", status.value());
        attributes.put("error", status.getReasonPhrase());
        attributes.put("message", message);
        attributes.put("path", ((ServletWebRequest) request).getRequest().getRequestURI());
        attributes.put("validationErrors", validationErrors);
        attributes.put("errorCode", errorCode);
        return new ResponseEntity<>(attributes, status);
    }
}
