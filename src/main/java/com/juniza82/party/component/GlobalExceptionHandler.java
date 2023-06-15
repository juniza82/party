package com.juniza82.party.component;

import com.callbuslab.review_test.api.global.excption.BusinessException;
import com.fasterxml.jackson.core.JsonParseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    /**
     * 주로 @RequestParam enum으로 binding 못했을 경우 발생
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ApiResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error("handleMethodArgumentTypeMismatchException", e);
        ApiResponse errorResponse = new ApiResponse(e.getMessage());
        return ResponseEntity.status(BAD_REQUEST).body(errorResponse);
    }

    /**
     * 지원하지 않은 HTTP method 호출 할 경우 발생
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ApiResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("handleHttpRequestMethodNotSupportedException", e);
        ApiResponse errorResponse = new ApiResponse(e.getMessage());
        return ResponseEntity.status(METHOD_NOT_ALLOWED).body(errorResponse);
    }

    /**
     * 비즈니스 로직 실행 중 오류 발생
     */
    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ApiResponse> handleBusinessException(BusinessException e) {
        log.error("handleBusinessException", e);
        ApiResponse errorResponse = new ApiResponse(e.getMessage());
        return ResponseEntity.status(e.getHttpStatus()).body(errorResponse);
    }

    /**
     * Json Parser 예외 발생
     */
    @ExceptionHandler(JsonParseException.class)
    protected ResponseEntity<ApiResponse> handleJsonParseException(JsonParseException e) {
        log.error("JsonParseException", e);
        ApiResponse errorResponse = new ApiResponse(e.getMessage());
        return ResponseEntity.status(BAD_REQUEST).body(errorResponse);
    }

    /**
     * 나머지 예외 발생
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ApiResponse> handleException(Exception e) {
        log.error("Exception", e);
        ApiResponse errorResponse = new ApiResponse(e.getMessage());
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}