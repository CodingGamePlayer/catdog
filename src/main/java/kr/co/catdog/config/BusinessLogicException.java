package kr.co.catdog.config;

import org.springframework.http.HttpHeaders;

public class BusinessLogicException extends Exception {
    private final ExceptionCode exceptionCode;

    public BusinessLogicException(String responseBody, HttpHeaders responseHeaders, ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }

    public ExceptionCode getExceptionCode() {
        return exceptionCode;
    }
}
