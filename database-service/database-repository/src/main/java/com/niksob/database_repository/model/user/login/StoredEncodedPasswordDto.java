package com.niksob.database_repository.model.user.login;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StoredEncodedPasswordDto {
    private String value;
}
