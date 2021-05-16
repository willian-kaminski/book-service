package com.kaminski.book.exception.handler;

import com.kaminski.book.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler {

    private static final String EXCEPTION_NOT_FOUND_TITLE = "Resource not found";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorDetails> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(","));
        String messages = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));

        ValidationErrorDetails validationErrorDetails = ValidationErrorDetails.builder()
                .title("Field Validation Error")
                .status(HttpStatus.BAD_REQUEST.value())
                .detail("Field Validation Error")
                .timestamp(new Date().getTime())
                .message(exception.getClass().getName())
                .field(fields)
                .fieldMessage(messages)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationErrorDetails);

    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionDetails> handleResourceNotFoundException(ResourceNotFoundException exception){

        ExceptionDetails resourceNotFoundDetails = ExceptionDetails.builder()
                .title(EXCEPTION_NOT_FOUND_TITLE)
                .status(HttpStatus.NOT_FOUND.value())
                .detail(exception.getMessage())
                .timestamp(new Date().getTime())
                .message(exception.getClass().getName())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resourceNotFoundDetails);

    }

    @ExceptionHandler(BookAlreadyRentedException.class)
    public ResponseEntity<ExceptionDetails> handleBookAlreadyRentedException(BookAlreadyRentedException exception){

        ExceptionDetails resourceNotFoundDetails = ExceptionDetails.builder()
                .title(EXCEPTION_NOT_FOUND_TITLE)
                .status(HttpStatus.NOT_FOUND.value())
                .detail(exception.getMessage())
                .timestamp(new Date().getTime())
                .message(exception.getClass().getName())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resourceNotFoundDetails);

    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ExceptionDetails> handleAuthException(AuthException exception){

        ExceptionDetails resourceNotFoundDetails = ExceptionDetails.builder()
                .title(EXCEPTION_NOT_FOUND_TITLE)
                .status(HttpStatus.NOT_FOUND.value())
                .detail(exception.getMessage())
                .timestamp(new Date().getTime())
                .message(exception.getClass().getName())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resourceNotFoundDetails);

    }

}
