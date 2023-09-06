package com.niksob.authorization.config.jwt;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public class SecretKeyDecoding {
    public static SecretKey decodeKey(String secretStr) {
        return secretStr == null ? null : Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretStr));
    }
}
