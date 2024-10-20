package com.fallt.hotel_reservation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class ExceptionHandlerController {


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleEntityNotFoundEx(Exception e) {
        ExceptionResponse body = ExceptionResponse.builder()
                .error("NOT FOUND")
                .timestamp(System.currentTimeMillis())
                .errorDescription(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleValidationException(MethodArgumentNotValidException ex) {
        String cause = Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage();
        ExceptionResponse body = ExceptionResponse.builder()
                .error("BAD REQUEST")
                .timestamp(System.currentTimeMillis())
                .errorDescription(cause)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(body);
    }

}
