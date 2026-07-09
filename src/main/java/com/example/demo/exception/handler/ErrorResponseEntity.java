package com.example.demo.exception.handler;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.*;

@Builder
public class ErrorResponseEntity<T> {
    protected String errorCode;
    @Builder.Default
    protected String message = "";
    @Builder.Default
    @JsonInclude(NON_NULL)
    protected String reqId = null;
    @JsonInclude(NON_NULL)
    private T errors;
    @JsonInclude(NON_NULL)
    private Object exception;
    public ErrorResponseEntity(String message) {
        this.message = message;
    }
    public ErrorResponseEntity(String message, String errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }
    @JsonGetter
    public String getMessage() {
        String r = message;
        if (isNull(r)) {
            r = "";
        }
        if (isNoneBlank(reqId) && !contains(r, "[") && !endsWith(r, "]")) {
            r += " [" + reqId + "]";
        }
        return r;
    }
    public <S> ErrorResponseEntity<T> wrap(S par, Consumer2<ErrorResponseEntity<T>, S> consumer) {
        if (Objects.nonNull(consumer)) {
            consumer.accept(this, par);
        }
        return this;
    }
}
