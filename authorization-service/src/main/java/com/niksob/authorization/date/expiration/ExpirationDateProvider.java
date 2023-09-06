package com.niksob.authorization.date.expiration;

import java.util.Date;

public interface ExpirationDateProvider {
    Date getByMinutes(int minutes);
}
