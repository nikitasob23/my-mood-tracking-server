package com.niksob.authorization.exception.handler;

import com.niksob.authorization_model.exception.response.ResponseStatusException;
import com.niksob.authorization_model.mapper.error.ErrorDetailsMapper;
import com.niksob.authorization_model.model.response.error.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {

    private ErrorDetailsMapper errorDetailsMapper;

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorDetails> handleResponseStatusException(ResponseStatusException ex) {

        final HttpStatus httpStatus = ex.getHttpStatus();
        final ErrorDetails errorDetails = errorDetailsMapper.fromResponseStatusException(ex);

        return ResponseEntity.status(httpStatus).body(errorDetails);
    }
}
