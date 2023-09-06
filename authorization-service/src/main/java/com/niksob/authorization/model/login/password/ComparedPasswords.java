package com.niksob.authorization.model.login.password;

import com.niksob.domain_model.model.user.login.password.EncodedPassword;
import com.niksob.domain_model.model.user.login.password.RowPassword;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ComparedPasswords {
    private RowPassword rowPassword;
    private EncodedPassword encodedPassword;
}
