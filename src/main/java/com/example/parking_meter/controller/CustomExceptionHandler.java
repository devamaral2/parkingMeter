package com.example.parking_meter.controller;

import com.example.parking_meter.controller.exception.StandardError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> handleValidationException(MethodArgumentNotValidException exception, HttpServletRequest request) {
        String errors =
                exception
                        .getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map(error -> error.getField() + ": " + error.getDefaultMessage())
                        .collect(Collectors.joining(", "));

        StandardError err = StandardError.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Está faltando alguma entidade para acessar a rota")
                .message(errors)
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<StandardError> handleValidationException(HttpMessageNotReadableException exception, HttpServletRequest request) {
        StandardError err = StandardError.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Provavelmente existe um erro no tipo da entidade enviada")
                .message(exception.getMessage())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(OptimisticLockingFailureException.class)
    public ResponseEntity<StandardError> handleOptimisticLockingFailureException(
            OptimisticLockingFailureException exception,
            HttpServletRequest request
    ) {
        StandardError err = StandardError.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.CONFLICT.value())
                .error("Erro de concorrência: O price foi atualizado por outro usuário, por favor tente novamente.")
                .message(exception.getMessage())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(err);
    }
}
