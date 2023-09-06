package com.niksob.authorization.service;

public interface ExecutableService<T, R> {
    R execute(T t);
}
