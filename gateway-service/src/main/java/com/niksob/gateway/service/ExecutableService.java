package com.niksob.gateway.service;

public interface ExecutableService<T, R> {
    R execute(T t);
}
