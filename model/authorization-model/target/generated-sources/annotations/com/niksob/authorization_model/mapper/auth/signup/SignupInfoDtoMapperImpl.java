package com.niksob.authorization_model.mapper.auth.signup;

import com.niksob.authorization_model.dto.signup.SignupDetailsDto;
import com.niksob.authorization_model.model.signup.RowSignupDetails;
import com.niksob.domain_model.model.user.login.password.RowPassword;
import com.niksob.domain_model.model.user.login.role.Role;
import com.niksob.domain_model.model.user.login.username.Username;
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
public class SignupInfoDtoMapperImpl implements SignupInfoDtoMapper {

    @Override
    public SignupDetailsDto toDto(RowSignupDetails rowSignupDetails) {
        if ( rowSignupDetails == null ) {
            return null;
        }

        String username = null;
        String rowPassword = null;
        String nickname = null;
        Set<String> roles = null;

        username = rowSignupDetailsUsernameValue( rowSignupDetails );
        rowPassword = rowSignupDetailsRowPasswordValue( rowSignupDetails );
        nickname = rowSignupDetails.nickname();
        roles = roleSetToStringSet( rowSignupDetails.roles() );

        SignupDetailsDto signupDetailsDto = new SignupDetailsDto( username, nickname, rowPassword, roles );

        return signupDetailsDto;
    }

    private String rowSignupDetailsUsernameValue(RowSignupDetails rowSignupDetails) {
        if ( rowSignupDetails == null ) {
            return null;
        }
        Username username = rowSignupDetails.username();
        if ( username == null ) {
            return null;
        }
        String value = username.value();
        if ( value == null ) {
            return null;
        }
        return value;
    }

    private String rowSignupDetailsRowPasswordValue(RowSignupDetails rowSignupDetails) {
        if ( rowSignupDetails == null ) {
            return null;
        }
        RowPassword rowPassword = rowSignupDetails.rowPassword();
        if ( rowPassword == null ) {
            return null;
        }
        String value = rowPassword.value();
        if ( value == null ) {
            return null;
        }
        return value;
    }

    protected Set<String> roleSetToStringSet(Set<Role> set) {
        if ( set == null ) {
            return null;
        }

        Set<String> set1 = new LinkedHashSet<String>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Role role : set ) {
            set1.add( role.name() );
        }

        return set1;
    }
}
