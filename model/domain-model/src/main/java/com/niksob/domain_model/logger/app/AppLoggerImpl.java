package com.niksob.domain_model.logger.app;

import lombok.NonNull;
import org.slf4j.Logger;

public abstract class AppLoggerImpl implements AppLogger {
    protected final Logger log;

    public AppLoggerImpl(@NonNull Logger log) {
        this.log = log;
    }

    @Override
    public void debug(@NonNull String s) {
        log.debug(s);
    }

    @Override
    public void debug(@NonNull String s, @NonNull Object... objects) {
        log.debug(s, objects);
    }

    @Override
    public void info(@NonNull String s) {
        log.info(s);
    }

    @Override
    public void info(@NonNull String s, @NonNull Object... objects) {
        log.info(s, objects);
    }

    @Override
    public void warn(@NonNull String s) {
        log.warn(s);
    }

    @Override
    public void warn(@NonNull String s, @NonNull Object... objects) {
        log.warn(s, objects);
    }

    @Override
    public void error(@NonNull String s) {
        log.error(s);
    }

    @Override
    public void error(@NonNull String s, @NonNull Object... objects) {
        log.error(s, objects);
    }
}
