package com.niksob.database_repository.model.user.login;

import com.niksob.database_repository.model.user.login.role.StoredRoleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class StoredUserLoginInfoDto {
    @NonNull
    private StoredUsernameDto username;
    private StoredEncodedPasswordDto encodedPassword;
    private Set<StoredRoleDto> roles;
}
