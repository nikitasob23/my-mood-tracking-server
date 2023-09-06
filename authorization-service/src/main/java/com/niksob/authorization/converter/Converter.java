package com.niksob.authorization.converter;

public interface Converter<T, R> {
    R convert(T t);
}
