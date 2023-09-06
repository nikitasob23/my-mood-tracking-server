package com.niksob.authorization_model.mapper.auth.token.details;

import com.niksob.authorization_model.model.login.RowLoginInDetails;
import com.niksob.authorization_model.model.signup.RowSignupDetails;
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
public class AuthTokenDetailsMapperImpl implements AuthTokenDetailsMapper {

    @Override
    public RowLoginInDetails toRowLoginInDetails(RowSignupDetails rowSignupDetails) {
        if ( rowSignupDetails == null ) {
            return null;
        }

        Username username = null;
        RowPassword rowPassword = null;

        username = rowSignupDetails.username();
        rowPassword = rowSignupDetails.rowPassword();

        RowLoginInDetails rowLoginInDetails = new RowLoginInDetails( username, rowPassword );

        return rowLoginInDetails;
    }
}
