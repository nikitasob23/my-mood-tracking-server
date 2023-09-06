package com.niksob.authorization.config.model.auth.login;

import com.niksob.authorization_model.dto.login.RowLoginInDetailsDto;
import com.niksob.authorization_model.model.login.RowLoginInDetails;
import com.niksob.domain_model.model.user.login.password.RowPassword;
import com.niksob.domain_model.model.user.login.username.Username;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestRowLoginInDetailsConfig {

    @Autowired
    private Username username;

    @Autowired
    private RowPassword rowPassword;

    @Bean
    public RowLoginInDetails getTestRowLoginInDetails() {
        return new RowLoginInDetails(username, rowPassword);
    }

    @Bean
    public RowLoginInDetailsDto getRowLoginInDetailsDto() {
        return new RowLoginInDetailsDto(username.value(), rowPassword.value());
    }
}
