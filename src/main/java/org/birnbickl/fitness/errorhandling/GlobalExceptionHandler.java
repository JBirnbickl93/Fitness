package org.birnbickl.fitness.errorhandling;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {


    // Exception, bei fehlgeschlagener Validierung
    @ExceptionHandler
    public ResponseEntity<ApiError> handleValidationException(MethodArgumentNotValidException exception) {
        List<String> errors;

        errors = exception.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).toList();

        ApiError apiError = new ApiError(LocalDateTime.now(), "Validation failed.", HttpStatus.BAD_REQUEST.value(), errors);
        return ResponseEntity.badRequest().body(apiError);
    }


    // Exception, für nicht auffindbares Element
    @ExceptionHandler
    public ResponseEntity<ApiError> handleNotFound (NoSuchElementException exception) {
        List<String> errors = List.of("The requested object was not found.");
        ApiError apiError = new ApiError(LocalDateTime.now(), "Not found.", HttpStatus.NOT_FOUND.value(), errors);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }


    // Exception, für Datenbank-Constraint Verletzungen
    @ExceptionHandler
    public ResponseEntity<ApiError> handleConstraintViolation (ConstraintViolation exception) {
        List<String> errors = exception.getConstraintViolations().stream().map(constraintViolation.getMessage())
                .toList();
        ApiError apiError = new ApiError(LocalDateTime.now(), "Constraint violation", HttpStatus.BAD_REQUEST.value(), errors);
        return ResponseEntity.badRequest().body(apiError);
    }

    // Exception, bei fehlendem Datenbankeintrag
    @ExceptionHandler
    public ResponseEntity<ApiError>handleEntityNotFound (EntityNotFoundException exception) {
        List<String> errors = List.of("The requested entity was not found.");
        ApiError apiError = new ApiError(LocalDateTime.now(), "Not found.", HttpStatus.NOT_FOUND.value(), errors);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }
    // Exception, für Fehler 500
    @ExceptionHandler
    public ResponseEntity<ApiError> handleGenericException (Exception exception) {
        List<String> errors = List.of(exception.getMessage());
        ApiError apiError = new ApiError(LocalDateTime.now(), "Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR.value(), errors);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
    }

    // Exception, bei bereits vergegebenem Usernamen
    @ExceptionHandler
    public ResponseEntity<ApiError> usernameAlreadyExists(UsernameAlreadyExistsException exception){
        List<String> errors = List.of(exception.getMessage());
        ApiError apiError = new ApiError(LocalDateTime.now(), "Username already exists.", HttpStatus.BAD_REQUEST.value(), errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    // Exception, bei falschen LoginDaten
    @ExceptionHandler
    public ResponseEntity<ApiError> invalidCredentialsException (InvalidParameterException exception) {
        List<String> errors = List.of(exception.getMessage());
        ApiError apiError = new ApiError(LocalDateTime.now(), "Invalid credentials.", HttpStatus.BAD_REQUEST.value(), errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }


}
