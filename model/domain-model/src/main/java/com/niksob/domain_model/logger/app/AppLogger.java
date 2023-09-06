package com.niksob.domain_model.logger.app;

public interface AppLogger {
    void debug(String s);

    void debug(String s, Object... objects);

    void info(String s);

    void info(String s, Object... objects);

    void warn(String s);

    void warn(String s, Object... objects);

    void error(String s);

    void error(String s, Object... objects);
}
