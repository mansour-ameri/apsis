package com.apsis.assignment.exxception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.UnexpectedTypeException;

/**
 * Can extend ResponseEntityExceptionHandler in case to override handel exceptions
 */
@Slf4j
@RestControllerAdvice
public class CounterControllerAdvice  {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> GlobalException(Exception exception){
        log.warn("Exception occur: ",exception);
        HttpStatus status;
        ErrorResponse errorDto;

        // Just Exception lists - We use only 2 exception for now
        if (exception instanceof HttpClientErrorException.BadRequest) {
            errorDto = new ErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }else if(exception instanceof UnexpectedTypeException) {
            errorDto = new ErrorResponse("Unexpected Type Exception", HttpStatus.BAD_REQUEST);
        } else {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            errorDto = new ErrorResponse("INTERNAL SERVER ERROR",HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return makeErrorResponseEntity(errorDto);
    }

    private ResponseEntity<ErrorResponse> makeErrorResponseEntity(ErrorResponse er){
        return ResponseEntity
                .status(er.getStatus())
                .body(er);
    }

    @Getter
    @AllArgsConstructor
    static class ErrorResponse{
        private String message;
        private HttpStatus status;
    }
}



