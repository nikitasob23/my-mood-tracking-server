package com.niksob.domain_model.model.token.refresh;

import com.niksob.domain_model.model.token.Token;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken implements Token {
    private String value;
}
