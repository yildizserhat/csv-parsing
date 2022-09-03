package com.yildiz.serhat.csvparsing.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class RecordExceptionHandler {

    @ExceptionHandler(FileNotValidException.class)
    public ResponseEntity<?> handleServiceExceptions(FileNotValidException exception) {
        log.error("Exception occurred: {}, httpStatus: {}", exception.getMessage(), exception.getHttpStatus());
        return ResponseEntity
                .status(exception.getHttpStatus())
                .body(exception.getMessage());
    }
}
