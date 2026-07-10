package com.example.demo.exception.handler;

import com.example.demo.exception.BusinessExceptionDemo;
import com.example.demo.exception.ErrorResponseDemo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BusinessExceptionHandlerDemo {

    @ExceptionHandler(BusinessExceptionDemo.class)
    public ResponseEntity<ErrorResponseDemo> handleBusinessException(BusinessExceptionDemo ex) {
         ErrorResponseDemo errorResponseDemo = new ErrorResponseDemo(ex.getMessage(), ex.getErrorCode());
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDemo);
    }
}
