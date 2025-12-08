package org.birnbickl.fitness.errorhandling;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ApiError> handleValidationException(MethodArgumentNotValidException exception) {
        List<String> errors;

        errors = exception.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).toList();

        ApiError apiError = new ApiError(LocalDateTime.now(), "Validation failed.", HttpStatus.BAD_REQUEST.value(), errors);
        return ResponseEntity.badRequest().body(apiError);
    }

}
