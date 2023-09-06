package com.niksob.authorization.service.user.creation;

import com.niksob.authorization_model.mapper.auth.login.SignupDetailsMapper;
import com.niksob.authorization_model.model.signup.SignupDetails;
import com.niksob.authorization_model.util.date.ActiveUserDetailsDateTimeUtil;
import com.niksob.domain_model.model.user.UserInfo;
import com.niksob.domain_model.model.user.active.ActiveUserDetails;
import com.niksob.domain_model.model.user.login.role.Role;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
@AllArgsConstructor
public class CreateDefUserByLoginDetailsServiceImpl implements CreateDefUserByLoginDetailsService {

    private final Set<Role> defRoles;
    private final ActiveUserDetailsDateTimeUtil activeUserDetailsDateTimeUtil;
    private final SignupDetailsMapper signupDetailsMapper;

    @Override
    public UserInfo execute(SignupDetails signupDetails) {

        final UserInfo userInfo = signupDetailsMapper.toUserInfo(signupDetails);

        ActiveUserDetails defActiveUserDetails = new ActiveUserDetails(
                true,
                true,
                activeUserDetailsDateTimeUtil.current(),
                activeUserDetailsDateTimeUtil.current()
        );

        userInfo.getLoginInfo().setRoles(defRoles);
        userInfo.setActiveDetails(defActiveUserDetails);
        return userInfo;
    }
}
