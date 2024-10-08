package ru.croc.team4.cinema.exception_handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BaseExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        ErrorResponse validationErrorResponse = new ErrorResponse("Ошибка переданных параметров", ex.getMessage());
        return ResponseEntity.badRequest().body(validationErrorResponse);
    }
}