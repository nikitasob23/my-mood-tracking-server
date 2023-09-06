package com.niksob.domain_model.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.niksob.domain_model.dto.token.refresh.RefreshTokenDto;
import com.niksob.domain_model.dto.user.active.ActiveUserDetailsDto;
import com.niksob.domain_model.dto.user.login.LoginInfoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserInfoDto {
    private String name;
    @NonNull
    @JsonProperty("login_info")
    private LoginInfoDto loginInfo;
    @JsonProperty("active_details")
    private ActiveUserDetailsDto activeDetails;
    @JsonProperty("refresh_token")
    private RefreshTokenDto refreshToken;
}
