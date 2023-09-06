package com.niksob.authorization_model.model.token.access;

import com.niksob.domain_model.model.token.Token;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessToken implements Token {
    private String value;
}
