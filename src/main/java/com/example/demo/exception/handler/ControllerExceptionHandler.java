package com.example.demo.exception.handler;

import com.example.demo.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Locale;

import static java.lang.String.format;

@Slf4j
public class ControllerExceptionHandler extends ExceptionHandlerBase{

    public ControllerExceptionHandler(MessageSource messageSource) {
        super(messageSource);
    }

    public ResponseEntity<?> businessExceptionHandler(BusinessException ex, Locale locale){
        String message = getI18nMessage(ex, locale);
        HttpStatus statusCode = getHttpStatusFromException(ex);
        log.info(format("业务错误: %s", errorMessage), ex);
        ErrorResponseEntity<?> entity = new ErrorResponseEntity<>(errorMessage, ex.getErrorCode())
                .errors(ex.getErrors())
                .wrap(ex, this::wrap);
        return ResponseEntity
                .status(statusCode)
                .body(entity);
    }
}
