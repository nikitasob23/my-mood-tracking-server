package com.niksob.database_repository.model.user;

import com.niksob.database_repository.model.token.refresh.stored.StoredRefreshTokenDto;
import com.niksob.database_repository.model.user.active.StoredActiveUserDetailsDto;
import com.niksob.database_repository.model.user.login.StoredUserLoginInfoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class StoredUserInfoDto {
    @NonNull
    private Long id;
    private String name;
    @NonNull
    private StoredUserLoginInfoDto loginInfo;
    private StoredActiveUserDetailsDto activeDetails;
    private StoredRefreshTokenDto refreshToken;
}
