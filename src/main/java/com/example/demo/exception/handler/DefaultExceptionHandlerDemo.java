package com.example.demo.exception.handler;

import com.example.demo.exception.ErrorResponseDemo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DefaultExceptionHandlerDemo {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDemo> defaultExceptionHandler(Exception e) {
        ErrorResponseDemo errorResponseDemo = new ErrorResponseDemo(e.getMessage(), "DEFAULT_ERROR");
        return ResponseEntity.status(500).body(errorResponseDemo);
    }
}
