package com.niksob.domain_model.model.user;

import com.niksob.domain_model.model.token.refresh.RefreshToken;
import com.niksob.domain_model.model.user.active.ActiveUserDetails;
import com.niksob.domain_model.model.user.login.LoginInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserInfo {
    private String name;
    @NonNull
    private LoginInfo loginInfo;
    private ActiveUserDetails activeDetails;
    private RefreshToken refreshToken;
}
