package com.niksob.authorization_model.util.date;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ActiveUserDetailsDateTimeUtilImpl implements ActiveUserDetailsDateTimeUtil {
    @Override
    public LocalDateTime current() {
        return LocalDateTime.now();
    }
}
