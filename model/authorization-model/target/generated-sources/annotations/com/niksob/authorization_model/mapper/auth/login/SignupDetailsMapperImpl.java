package com.niksob.authorization_model.mapper.auth.login;

import com.niksob.authorization_model.model.signup.SignupDetails;
import com.niksob.domain_model.model.user.UserInfo;
import com.niksob.domain_model.model.user.login.LoginInfo;
import com.niksob.domain_model.model.user.login.role.Role;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-06T23:23:49+0400",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.5 (Oracle Corporation)"
)
@Component
public class SignupDetailsMapperImpl implements SignupDetailsMapper {

    @Override
    public UserInfo toUserInfo(SignupDetails signupDetails) {
        if ( signupDetails == null ) {
            return null;
        }

        UserInfo userInfo = new UserInfo();

        userInfo.setLoginInfo( signupDetailsToLoginInfo( signupDetails ) );
        userInfo.setName( signupDetails.nickname() );

        return userInfo;
    }

    protected LoginInfo signupDetailsToLoginInfo(SignupDetails signupDetails) {
        if ( signupDetails == null ) {
            return null;
        }

        LoginInfo loginInfo = new LoginInfo();

        loginInfo.setUsername( signupDetails.username() );
        loginInfo.setEncodedPassword( signupDetails.encodedPassword() );
        Set<Role> set = signupDetails.roles();
        if ( set != null ) {
            loginInfo.setRoles( new LinkedHashSet<Role>( set ) );
        }

        return loginInfo;
    }
}
