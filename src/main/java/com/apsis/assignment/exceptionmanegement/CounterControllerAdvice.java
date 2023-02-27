package com.apsis.assignment.exceptionmanegement;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/**
 * Can extend ResponseEntityExceptionHandler in case to override handel exceptions
 */
@Slf4j
@RestControllerAdvice
public class CounterControllerAdvice  {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> GlobalException(Exception exception){
        ErrorResponse errorResponse;
        log.error(exception.getMessage());
        switch (exception.getClass().getSimpleName()) {
            case "BadRequest":
                errorResponse = new ErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
                break;
            case "UnexpectedTypeException":
                errorResponse = new ErrorResponse("Unexpected Type Exception", HttpStatus.BAD_REQUEST, LocalDateTime.now());
                break;
            case "IllegalArgumentException":
                errorResponse = new ErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
                break;
            case "NoSuchElementException":
                errorResponse = new ErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now());
                break;
            default:
                errorResponse = new ErrorResponse("INTERNAL SERVER ERROR", HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        }
        return createResponseEntity(errorResponse);
    }

    private ResponseEntity<ErrorResponse> createResponseEntity(ErrorResponse er){
        return ResponseEntity
                .status(er.getStatus())
                .body(er);
    }

    @Getter
    @AllArgsConstructor
    static class ErrorResponse{
        private String message;
        private HttpStatus status;
        private LocalDateTime timestamp;
    }
}



