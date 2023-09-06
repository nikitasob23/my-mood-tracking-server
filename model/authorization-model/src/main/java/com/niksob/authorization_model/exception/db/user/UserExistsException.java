package com.niksob.authorization_model.exception.db.user;

public class UserExistsException extends RuntimeException {

    public UserExistsException() {
    }

    public UserExistsException(String message) {
    }

    public UserExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserExistsException(Throwable cause) {
        super(cause);
    }

    public UserExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
