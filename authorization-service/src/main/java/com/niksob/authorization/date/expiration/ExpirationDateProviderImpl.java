package com.niksob.authorization.date.expiration;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.stream.Stream;

@Component
public class ExpirationDateProviderImpl implements ExpirationDateProvider {
    @Override
    public Date getByMinutes(int minutes) {
        return Stream.generate(LocalDateTime::now).limit(1)
                .map(now -> now.plusMinutes(minutes))
                .map(now -> now.atZone(ZoneId.systemDefault()))
                .map(ZonedDateTime::toInstant)
                .map(Date::from)
                .findFirst().orElse(null);
    }
}
