package dev.guilhermepisco.msscbeerservice.web.controller.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class MvcExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> validationErrorHandler(MethodArgumentNotValidException ex){
        return ResponseEntity.badRequest().body(getErrorsFromException(ex));
    }

    private List<String> getErrorsFromException(MethodArgumentNotValidException ex) {
        return ex.getFieldErrors()
                .stream()
                .map(this::buildErrorMessage)
                .toList();
    }

    private String buildErrorMessage(FieldError fieldError){
        return fieldError.getField() + " : " + fieldError.getDefaultMessage();
    }

}
