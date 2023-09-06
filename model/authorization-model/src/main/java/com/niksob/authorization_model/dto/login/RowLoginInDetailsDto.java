package com.niksob.authorization_model.dto.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RowLoginInDetailsDto {
    private String username;
    private String rowPassword;
}
