package com.niksob.authorization_model.mapper.auth.signup;

import com.niksob.authorization_model.dto.signup.RowUserSignupDetailsDto;
import com.niksob.authorization_model.model.signup.RowUserSignupDetails;
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
public class SignupDetailsDtoMapperImpl implements SignupDetailsDtoMapper {

    @Override
    public RowUserSignupDetails fromDto(RowUserSignupDetailsDto dto) {
        if ( dto == null ) {
            return null;
        }

        Username username = null;
        RowPassword rowPassword = null;
        String nickname = null;

        username = rowUserSignupDetailsDtoToUsername( dto );
        rowPassword = rowUserSignupDetailsDtoToRowPassword( dto );
        nickname = dto.getNickname();

        RowUserSignupDetails rowUserSignupDetails = new RowUserSignupDetails( username, nickname, rowPassword );

        return rowUserSignupDetails;
    }

    protected Username rowUserSignupDetailsDtoToUsername(RowUserSignupDetailsDto rowUserSignupDetailsDto) {
        if ( rowUserSignupDetailsDto == null ) {
            return null;
        }

        String value = null;

        value = rowUserSignupDetailsDto.getEmail();

        Username username = new Username( value );

        return username;
    }

    protected RowPassword rowUserSignupDetailsDtoToRowPassword(RowUserSignupDetailsDto rowUserSignupDetailsDto) {
        if ( rowUserSignupDetailsDto == null ) {
            return null;
        }

        String value = null;

        value = rowUserSignupDetailsDto.getRowPassword();

        RowPassword rowPassword = new RowPassword( value );

        return rowPassword;
    }
}
