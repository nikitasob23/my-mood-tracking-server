package com.niksob.authorization_model.mapper.auth.login;

import com.niksob.authorization_model.dto.login.RowLoginInDetailsDto;
import com.niksob.authorization_model.model.login.UserLoginInDetails;
import com.niksob.domain_model.model.user.login.password.RowPassword;
import com.niksob.domain_model.model.user.login.username.Username;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-06T23:23:49+0400",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.5 (Oracle Corporation)"
)
@Component
public class UserLoginInDetailsDtoMapperImpl implements UserLoginInDetailsDtoMapper {

    @Override
    public UserLoginInDetails fromDto(RowLoginInDetailsDto dto) {
        if ( dto == null ) {
            return null;
        }

        Username username = null;
        RowPassword rowPassword = null;

        username = rowLoginInDetailsDtoToUsername( dto );
        rowPassword = rowLoginInDetailsDtoToRowPassword( dto );

        UserLoginInDetails userLoginInDetails = new UserLoginInDetails( username, rowPassword );

        return userLoginInDetails;
    }

    protected Username rowLoginInDetailsDtoToUsername(RowLoginInDetailsDto rowLoginInDetailsDto) {
        if ( rowLoginInDetailsDto == null ) {
            return null;
        }

        String value = null;

        value = rowLoginInDetailsDto.getUsername();

        Username username = new Username( value );

        return username;
    }

    protected RowPassword rowLoginInDetailsDtoToRowPassword(RowLoginInDetailsDto rowLoginInDetailsDto) {
        if ( rowLoginInDetailsDto == null ) {
            return null;
        }

        String value = null;

        value = rowLoginInDetailsDto.getRowPassword();

        RowPassword rowPassword = new RowPassword( value );

        return rowPassword;
    }
}
