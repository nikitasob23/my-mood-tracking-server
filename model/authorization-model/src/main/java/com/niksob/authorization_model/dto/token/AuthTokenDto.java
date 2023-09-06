package com.niksob.authorization_model.dto.token;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthTokenDto {
    private String access;
    private String refresh;
}
