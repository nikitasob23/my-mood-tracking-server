package com.niksob.domain_model.logger.app.stacktrace;

import com.niksob.domain_model.logger.app.AppLogger;

public interface StackTraceLogger extends AppLogger {
    void warnWithStackTrace(String s, StackTraceElement[] stackTrace, Object... objects);

    void errorWithStackTrace(String s, StackTraceElement[] stackTrace, Object... objects);
}
