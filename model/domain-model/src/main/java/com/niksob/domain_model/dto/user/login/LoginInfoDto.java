package com.niksob.domain_model.dto.user.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class LoginInfoDto {
    private String username;
    private String password;
    private Set<String> roles;
}
