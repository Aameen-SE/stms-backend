package com.aameen.stms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex){

        Map<String,String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        ErrorResponse response = ErrorResponse.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .errors(errors)
                .build();

        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<?> handleDuplicateException(DuplicateResourceException ex){
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(Map.of(
                        "timestamp", LocalDateTime.now(),
                        "status", HttpStatus.CONFLICT.value(),
                        "message", ex.getMessage()
                ));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleUserNotFound(UserNotFoundException ex) {

        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("status", 404);
        error.put("message", ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidPassword(InvalidPasswordException ex) {

        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("status", 401);
        error.put("message", ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }
}


