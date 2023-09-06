package com.niksob.domain_model.mapper.user.login;

import com.niksob.domain_model.dto.user.login.LoginInfoDto;
import com.niksob.domain_model.model.user.login.LoginInfo;
import com.niksob.domain_model.model.user.login.password.EncodedPassword;
import com.niksob.domain_model.model.user.login.role.Role;
import com.niksob.domain_model.model.user.login.username.Username;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-06T23:23:48+0400",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.5 (Oracle Corporation)"
)
@Component
public class UserLoginInfoDtoMapperImpl implements UserLoginInfoDtoMapper {

    @Override
    public LoginInfoDto toDto(LoginInfo dto) {
        if ( dto == null ) {
            return null;
        }

        LoginInfoDto loginInfoDto = new LoginInfoDto();

        loginInfoDto.setUsername( dtoUsernameValue( dto ) );
        loginInfoDto.setPassword( dtoEncodedPasswordValue( dto ) );
        loginInfoDto.setRoles( roleSetToStringSet( dto.getRoles() ) );

        return loginInfoDto;
    }

    @Override
    public LoginInfo fromDto(LoginInfoDto dto) {
        if ( dto == null ) {
            return null;
        }

        LoginInfo loginInfo = new LoginInfo();

        loginInfo.setUsername( loginInfoDtoToUsername( dto ) );
        loginInfo.setEncodedPassword( loginInfoDtoToEncodedPassword( dto ) );
        loginInfo.setRoles( stringSetToRoleSet( dto.getRoles() ) );

        return loginInfo;
    }

    private String dtoUsernameValue(LoginInfo loginInfo) {
        if ( loginInfo == null ) {
            return null;
        }
        Username username = loginInfo.getUsername();
        if ( username == null ) {
            return null;
        }
        String value = username.value();
        if ( value == null ) {
            return null;
        }
        return value;
    }

    private String dtoEncodedPasswordValue(LoginInfo loginInfo) {
        if ( loginInfo == null ) {
            return null;
        }
        EncodedPassword encodedPassword = loginInfo.getEncodedPassword();
        if ( encodedPassword == null ) {
            return null;
        }
        String value = encodedPassword.value();
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

    protected Username loginInfoDtoToUsername(LoginInfoDto loginInfoDto) {
        if ( loginInfoDto == null ) {
            return null;
        }

        String value = null;

        value = loginInfoDto.getUsername();

        Username username = new Username( value );

        return username;
    }

    protected EncodedPassword loginInfoDtoToEncodedPassword(LoginInfoDto loginInfoDto) {
        if ( loginInfoDto == null ) {
            return null;
        }

        String value = null;

        value = loginInfoDto.getPassword();

        EncodedPassword encodedPassword = new EncodedPassword( value );

        return encodedPassword;
    }

    protected Set<Role> stringSetToRoleSet(Set<String> set) {
        if ( set == null ) {
            return null;
        }

        Set<Role> set1 = new LinkedHashSet<Role>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( String string : set ) {
            set1.add( Enum.valueOf( Role.class, string ) );
        }

        return set1;
    }
}
