package com.example.demo.exception;

import lombok.Getter;

//实现异常全局统一处理必须使用非受检异常，例如：RuntimeException. Exception是受检异常，异常往上抛出时必须要try catch处理或者方法签名上throw，RuntimeException是非受检异常，异常往上抛出时不需要try catch处理
@Getter
public class BusinessExceptionDemo extends RuntimeException{
    private final String errorCode;

    //只有throwable有message字段，所以他的子类都不需要自己构建message字段
    public BusinessExceptionDemo(String message){
        this(message, "BUSINESS_ERROR");
    }

    public BusinessExceptionDemo(String message, String errorCode){
        super(message);
        this.errorCode = errorCode;
    }

}
