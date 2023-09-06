package com.niksob.authorization.converter.user.row;

import com.niksob.authorization_model.dto.login.RowLoginInDetailsDto;
import com.niksob.authorization_model.model.login.RowLoginInDetails;
import com.niksob.domain_model.model.user.login.username.Username;
import com.niksob.domain_model.model.user.login.password.RowPassword;
import org.springframework.stereotype.Component;

@Component
public class RowUserDetailsConverterImpl implements RowUserDetailsConverter {
    @Override
    public RowLoginInDetails convert(RowLoginInDetailsDto rowLoginInDetailsDto) {
        return new RowLoginInDetails(
                new Username(rowLoginInDetailsDto.getUsername()),
                new RowPassword(rowLoginInDetailsDto.getRowPassword())
        );
    }
}
