package com.niksob.gateway.dto.user.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SecurityUserDetailsDto {
    private String username;
    private String encodedPassword;
    private Set<String> authorities;
    private boolean nonExpired;
    private boolean nonLocked;
    private boolean credentialsNonExpired;
    private boolean enable;
}
