package com.niksob.domain_model.exception.rest.status;

import org.springframework.http.HttpStatus;

public class HttpStatusException extends RuntimeException {

    private static final HttpStatus DEF_HTTP_STATUS = HttpStatus.INTERNAL_SERVER_ERROR;

    private final HttpStatus httpStatus;

    public HttpStatusException() {
        this.httpStatus = DEF_HTTP_STATUS;
    }

    public HttpStatusException(String message) {
        super(message);
        this.httpStatus = DEF_HTTP_STATUS;
    }

    public HttpStatusException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatusException(String message, Throwable cause) {
        super(message, cause);
        this.httpStatus = DEF_HTTP_STATUS;
    }

    public HttpStatusException(HttpStatus httpStatus, String message, Throwable cause) {
        super(message, cause);
        this.httpStatus = httpStatus;
    }

    public HttpStatusException(Throwable cause) {
        super(cause);
        this.httpStatus = DEF_HTTP_STATUS;
    }

    public HttpStatusException(
            HttpStatus httpStatus,
            String message,
            Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace
    ) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
