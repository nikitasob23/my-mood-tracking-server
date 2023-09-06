package com.niksob.authorization_model.mapper.auth.login;

import com.niksob.authorization_model.dto.login.RowLoginInDetailsDto;
import com.niksob.authorization_model.model.login.RowLoginInDetails;
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
public class RowLoginInDetailsDtoMapperImpl implements RowLoginInDetailsDtoMapper {

    @Override
    public RowLoginInDetails fromDto(RowLoginInDetailsDto dto) {
        if ( dto == null ) {
            return null;
        }

        Username username = null;
        RowPassword rowPassword = null;

        username = rowLoginInDetailsDtoToUsername( dto );
        rowPassword = rowLoginInDetailsDtoToRowPassword( dto );

        RowLoginInDetails rowLoginInDetails = new RowLoginInDetails( username, rowPassword );

        return rowLoginInDetails;
    }

    @Override
    public RowLoginInDetailsDto toDto(RowLoginInDetails rowLoginInDetails) {
        if ( rowLoginInDetails == null ) {
            return null;
        }

        RowLoginInDetailsDto rowLoginInDetailsDto = new RowLoginInDetailsDto();

        rowLoginInDetailsDto.setUsername( rowLoginInDetailsUsernameValue( rowLoginInDetails ) );
        rowLoginInDetailsDto.setRowPassword( rowLoginInDetailsRowPasswordValue( rowLoginInDetails ) );

        return rowLoginInDetailsDto;
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

    private String rowLoginInDetailsUsernameValue(RowLoginInDetails rowLoginInDetails) {
        if ( rowLoginInDetails == null ) {
            return null;
        }
        Username username = rowLoginInDetails.username();
        if ( username == null ) {
            return null;
        }
        String value = username.value();
        if ( value == null ) {
            return null;
        }
        return value;
    }

    private String rowLoginInDetailsRowPasswordValue(RowLoginInDetails rowLoginInDetails) {
        if ( rowLoginInDetails == null ) {
            return null;
        }
        RowPassword rowPassword = rowLoginInDetails.rowPassword();
        if ( rowPassword == null ) {
            return null;
        }
        String value = rowPassword.value();
        if ( value == null ) {
            return null;
        }
        return value;
    }
}
