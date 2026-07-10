//package com.example.demo.exception;
//
//import lombok.Getter;
//import lombok.NonNull;
//import lombok.experimental.StandardException;
//import org.antlr.v4.runtime.misc.NotNull;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ResponseStatus;
//
//import java.text.MessageFormat;
//import java.util.HashMap;
//import java.util.Map;
//
//@StandardException
//@ResponseStatus(HttpStatus.BAD_REQUEST)
//public class BusinessException extends RuntimeException{
//    @Getter
//    private final Map<String, Object> errors = new HashMap<>();
//
//    @Getter
//    private String errorCode;
//
//    public BusinessException(String message, Object... args){
//        super(MessageFormat.format(message, args));
//    }
//
//    public BusinessException code(String code){
//        this.errorCode = code;
//        return this;
//    }
//
//    public BusinessException putData(@NonNull String key, @NonNull Object value){
//        this.errors.put(key, value);
//        return this;
//    }
//}
