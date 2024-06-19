package com.system.EmployeeManagementSystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = APIRequestException.class)
    public ResponseEntity<APIException> handleAPIRequestException(APIRequestException e) {
        APIException apiException = APIException.builder()
                .message(e.getMessage())
                .throwable(e)
                .httpStatus(HttpStatus.BAD_REQUEST)
                .dateTime(ZonedDateTime.now())
                .build();
        return new ResponseEntity<>(apiException, apiException.getHttpStatus());
    }
}
