package com.niksob.authorization_model.exception.auth.token;

public class ExpiredAuthTokenException extends AuthTokenException {

    public ExpiredAuthTokenException() {
    }

    public ExpiredAuthTokenException(String message) {
        super(message);
    }

    public ExpiredAuthTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExpiredAuthTokenException(Throwable cause) {
        super(cause);
    }

    public ExpiredAuthTokenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
