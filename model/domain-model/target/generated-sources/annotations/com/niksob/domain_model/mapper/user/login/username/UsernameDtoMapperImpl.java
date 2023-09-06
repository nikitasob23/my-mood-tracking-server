package com.niksob.domain_model.mapper.user.login.username;

import com.niksob.domain_model.dto.user.login.username.UsernameDto;
import com.niksob.domain_model.model.user.login.username.Username;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-06T23:23:48+0400",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.5 (Oracle Corporation)"
)
@Component
public class UsernameDtoMapperImpl implements UsernameDtoMapper {

    @Override
    public UsernameDto toDto(Username username) {
        if ( username == null ) {
            return null;
        }

        String value = null;

        value = username.value();

        UsernameDto usernameDto = new UsernameDto( value );

        return usernameDto;
    }

    @Override
    public Username fromDto(UsernameDto usernameDto) {
        if ( usernameDto == null ) {
            return null;
        }

        String value = null;

        value = usernameDto.getValue();

        Username username = new Username( value );

        return username;
    }
}
