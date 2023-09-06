package com.niksob.database_repository.mapper.token.refresh.stored;

import com.niksob.database_repository.model.token.refresh.stored.StoredRefreshTokenDto;
import com.niksob.domain_model.model.token.refresh.RefreshToken;
import org.mapstruct.factory.Mappers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RefreshTokenToStoredDtoMapperTest {

    private final RefreshTokenToStoredDtoMapper refreshTokenToStoredDtoMapper =
            Mappers.getMapper(RefreshTokenToStoredDtoMapper.class);

    private RefreshToken refreshToken;

    private StoredRefreshTokenDto storedRefreshTokenDto;

    @Test
    public void testRefreshTokenToStoredDtoMapping() {
        final StoredRefreshTokenDto resultStoredRefreshTokenDto = refreshTokenToStoredDtoMapper.toDto(refreshToken);
        assertThat(resultStoredRefreshTokenDto).isEqualTo(storedRefreshTokenDto);
    }

    @Test
    public void testRefreshTokenFromStoredDtoMapping() {
        final RefreshToken resultRefreshToken = refreshTokenToStoredDtoMapper.fromDto(storedRefreshTokenDto);
        assertThat(resultRefreshToken).isEqualTo(refreshToken);
    }

    @BeforeEach
    public void prepare() {
        final String refreshTokenStr = "1234567890";

        storedRefreshTokenDto = new StoredRefreshTokenDto(refreshTokenStr);
        refreshToken = new RefreshToken(refreshTokenStr);
    }
}
