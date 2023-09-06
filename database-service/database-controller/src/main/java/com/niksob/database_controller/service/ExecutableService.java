package com.niksob.database_controller.service;

public interface ExecutableService<T, R> {
    R execute(T t);
}
