package com.niksob.authorization_model.model.token;

import com.niksob.authorization_model.model.token.access.AccessToken;
import com.niksob.domain_model.model.token.refresh.RefreshToken;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthToken {
    private AccessToken access;
    private RefreshToken refresh;
}
