package com.niksob.domain_model.dto.token.refresh;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenDto {
    private String value;
}