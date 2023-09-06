package com.niksob.database_repository.model.token.refresh.stored;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StoredRefreshTokenDto {
    private String value;
}
