package com.niksob.authorization_model.dto.signup;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RowUserSignupDetailsDto {
    private String email;
    private String nickname;
    private String rowPassword;
}
