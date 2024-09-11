package com.scotiabank.ms.student.exception;

import com.scotiabank.ms.student.model.response.ErrorResponse;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorResponse handleConflicException(ConflictException ce) {
        return ErrorResponse.builder()
                .error(ce.getErrorCode())
                .name(ce.getErrorName())
                .message(ce.getErrorMsg())
                .build();
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleConflicException(BadRequestException ce) {
        return ErrorResponse.builder()
                .error(ce.getErrorCode())
                .name(ce.getErrorName())
                .message(ce.getErrorMsg())
                .build();
    }

    @ExceptionHandler(WebExchangeBindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleValidationExceptions(WebExchangeBindException ex) {
        StringBuilder errors = new StringBuilder();
        ex.getBindingResult().getAllErrors().forEach((ObjectError error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.append(fieldName).append(": ").append(errorMessage).append(" | ");
        });
        return ErrorResponse.builder()
                .name(HttpStatus.BAD_REQUEST.name())
                .error(HttpStatus.BAD_REQUEST.value())
                .message(errors.toString())
                .build();
    }


}
