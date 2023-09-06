package com.niksob.domain_model.logger.app.stacktrace.object_state;

import com.niksob.domain_model.logger.app.stacktrace.StackTraceLogger;

public interface ObjectStateLogger extends StackTraceLogger {
    <T> void debugWithObjectState(String s, T objectState, Object... objects);

    <T> void infoWithObjectState(String s, T objectState, Object... objects);

    <T> void warnWithObjectState(String s, T objectState, Object... objects);

    <T> void warnWithStackTraceAndObjectState(String s, T objectState, StackTraceElement[] stackTrace, Object... objects);

    <T> void errorWithObjectState(String s, T objectState, Object... objects);

    <T> void errorWithStackTraceAndObjectState(String s, T objectState, StackTraceElement[] stackTrace, Object... objects);
}
