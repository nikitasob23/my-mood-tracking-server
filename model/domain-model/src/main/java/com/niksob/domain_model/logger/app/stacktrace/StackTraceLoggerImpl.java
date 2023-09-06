package com.niksob.domain_model.logger.app.stacktrace;

import com.niksob.domain_model.logger.app.AppLoggerImpl;
import com.niksob.domain_model.mapper.gson.JsonMapper;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

public class StackTraceLoggerImpl extends AppLoggerImpl implements StackTraceLogger {

    @Qualifier("json_mapper")
    protected final JsonMapper jsonMapper;

    @Value("${logger.message.field.stack-trace-key}")
    private String stackTraceKey;

    public StackTraceLoggerImpl(@NonNull Logger log, @NonNull JsonMapper jsonMapper) {
        super(log);
        this.jsonMapper = jsonMapper;
    }

    @Override
    public void warnWithStackTrace(String s, StackTraceElement[] stackTrace, Object... objects) {
        logWithStackTrace(() -> this.warn(s, objects), stackTrace);
    }

    @Override
    public void errorWithStackTrace(String s, StackTraceElement[] stackTrace, Object... objects) {
        logWithStackTrace(() -> this.error(s, objects), stackTrace);
    }

    private void logWithStackTrace(Runnable logging, StackTraceElement[] stackTrace) {
        MDC.put(stackTraceKey, jsonMapper.toJson(stackTrace));
        logging.run();
        MDC.remove(stackTraceKey);
    }
}
