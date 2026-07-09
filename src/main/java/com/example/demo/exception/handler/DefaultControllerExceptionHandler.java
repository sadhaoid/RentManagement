package com.example.demo.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.AsyncRequestNotUsableException;

import java.util.Locale;

import static java.lang.String.format;

@Slf4j
public class DefaultControllerExceptionHandler extends ExceptionHandlerBase {
    public DefaultControllerExceptionHandler(MessageSource messageSource) {
        super(messageSource);
    }
    @SuppressWarnings("rawtypes")
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity handleExceptions(Exception ex, Locale locale, HttpServletRequest request) {
        // 检测 SSE 请求或客户端断开异常
        String contentType = request.getContentType();
        String accept = request.getHeader("Accept");
        boolean isSseRequest = (contentType != null && contentType.contains(MediaType.TEXT_EVENT_STREAM_VALUE))
                || (accept != null && accept.contains(MediaType.TEXT_EVENT_STREAM_VALUE));
        // 客户端断开连接异常（SSE 常见情况）
        if (ex instanceof AsyncRequestNotUsableException ||
                (ex.getCause() != null && ex.getCause() instanceof java.io.IOException &&
                        ex.getCause().getMessage() != null && ex.getCause().getMessage().contains("Broken pipe"))) {
            log.debug("SSE client disconnected: {}", ex.getMessage());
            // 对于已断开的连接，不要尝试写响应
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        // 对于 SSE 请求的其他异常，也避免写入 ErrorResponseEntity
        if (isSseRequest) {
            log.error("SSE request error: {}", ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        // 标准异常处理
        String errorMessage = ex.getMessage();
        HttpStatus statusCode = getHttpStatusFromException(ex);
        log.error(format("系统错误: %s", errorMessage), ex);
        return ResponseEntity
                .status(statusCode)
                .body(new ErrorResponseEntity<>(errorMessage).wrap(ex, this::wrap));
    }
}
