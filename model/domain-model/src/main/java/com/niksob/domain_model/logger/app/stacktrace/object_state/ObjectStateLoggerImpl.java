package com.niksob.domain_model.logger.app.stacktrace.object_state;

import com.niksob.domain_model.logger.app.stacktrace.StackTraceLoggerImpl;
import com.niksob.domain_model.mapper.gson.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.MDC;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

public class ObjectStateLoggerImpl extends StackTraceLoggerImpl implements ObjectStateLogger {
    @Value("${logger.message.field.object-state-key}")
    private String objectStateKey;

    public ObjectStateLoggerImpl(@NonNull Logger log, @NonNull @Qualifier("json_mapper") JsonMapper jsonMapper) {
        super(log, jsonMapper);
    }

    @Override
    public <T> void debugWithObjectState(String s, T objectState, Object... objects) {
        logWithObjectState(() -> this.debug(s, objects), objectState);
    }

    @Override
    public <T> void infoWithObjectState(String s, T objectState, Object... objects) {
        logWithObjectState(() -> this.info(s, objects), objectState);
    }

    @Override
    public <T> void warnWithObjectState(String s, T objectState, Object... objects) {
        logWithObjectState(() -> this.warn(s, objects), objectState);
    }

    @Override
    public <T> void warnWithStackTraceAndObjectState(
            String s,
            T objectState,
            StackTraceElement[] stackTrace,
            Object... objects
    ) {
        logWithObjectState(() -> this.warnWithStackTrace(s, stackTrace, objects), objectState);
    }

    @Override
    public <T> void errorWithObjectState(String s, T objectState, Object... objects) {
        logWithObjectState(() -> this.error(s, objects), objectState);
    }

    @Override
    public <T> void errorWithStackTraceAndObjectState(
            String s,
            T objectState,
            StackTraceElement[] stackTrace,
            Object... objects
    ) {
        logWithObjectState(() -> this.errorWithStackTrace(s, stackTrace, objects), objectState);
    }

    private <T> void logWithObjectState(Runnable logging, T objectState) {
        MDC.put(objectStateKey, jsonMapper.toJson(objectState));
        logging.run();
        MDC.remove(objectStateKey);
    }
}
