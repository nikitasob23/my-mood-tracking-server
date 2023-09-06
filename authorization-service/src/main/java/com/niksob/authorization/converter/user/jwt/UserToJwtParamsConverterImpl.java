package com.niksob.authorization.converter.user.jwt;

import com.niksob.authorization.model.jwt.JwtClaims;
import com.niksob.authorization.model.jwt.JwtParams;
import com.niksob.domain_model.model.user.UserInfo;
import com.niksob.domain_model.model.user.login.role.Role;
import com.niksob.authorization.values.user.RoleKey;
import lombok.AllArgsConstructor;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public class UserToJwtParamsConverterImpl implements UserToJwtParamsConverter {

    private final RoleKey rolesKey;

    private final int expirationInMinutes;

    @Override
    public JwtParams convert(UserInfo userInfo) {
        return new JwtParams(
                userInfo.getLoginInfo().getUsername().value(),
                generateClaims(userInfo.getLoginInfo().getRoles()),
                expirationInMinutes
        );
    }

    private JwtClaims generateClaims(Set<Role> roles) {
        return new JwtClaims(
                Map.of(rolesKey.name(), roles.stream()
                        .map(Role::name)
                        .collect(Collectors.toSet()))
        );
    }
}
