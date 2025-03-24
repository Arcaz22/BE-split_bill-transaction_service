package com.splitbill.transaction.factory.exception;

import org.springframework.http.HttpStatus;

public class AppException extends RuntimeException {

    private final int statusCode;

    public AppException(String message) {
        super(message);
        this.statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

    public AppException(String message, HttpStatus status) {
        super(message);
        this.statusCode = status.value();
    }

    public AppException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public AppException(String errorMessage, HttpStatus status, Throwable err) {
        super(errorMessage, err);
        this.statusCode = status.value();
    }

    public AppException(String errorMessage, int statusCode, Throwable err) {
        super(errorMessage, err);
        this.statusCode = statusCode;
    }

    public HttpStatus getStatus() {
        return HttpStatus.valueOf(statusCode);
    }

    public int getStatusCode() {
        return this.statusCode;
    }

}
