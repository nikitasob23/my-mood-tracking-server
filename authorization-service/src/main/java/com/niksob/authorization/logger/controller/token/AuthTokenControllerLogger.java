package com.niksob.authorization.logger.controller.token;

import lombok.NonNull;

public interface AuthTokenControllerLogger {

    <T> void onSuccess(@NonNull T o);

    <T> void onError(@NonNull Object marker, @NonNull T objectState, @NonNull Throwable throwable);
}
