package com.niksob.database_repository.mapper.user.entity;

import com.niksob.database_repository.entity.refresh_token.RefreshTokenEntity;
import com.niksob.database_repository.entity.user.RoleEntity;
import com.niksob.database_repository.entity.user.UserEntity;
import com.niksob.database_repository.mapper.user.UserEntityToStoredDtoMapper;
import com.niksob.database_repository.model.token.refresh.stored.StoredRefreshTokenDto;
import com.niksob.database_repository.model.user.StoredUserInfoDto;
import com.niksob.database_repository.model.user.active.StoredActiveUserDetailsDto;
import com.niksob.database_repository.model.user.login.StoredEncodedPasswordDto;
import com.niksob.database_repository.model.user.login.StoredUserLoginInfoDto;
import com.niksob.database_repository.model.user.login.StoredUsernameDto;
import com.niksob.database_repository.model.user.login.role.StoredRoleDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserEntityToStoredDtoMapperTest {
    @Autowired
    private UserEntityToStoredDtoMapper userEntityToStoredDtoMapper;

    private UserEntity userEntity;

    private StoredUserInfoDto storedUserInfoDto;

    @Test
    public void testUserEntityToStoredDtoMapping() {
        final StoredUserInfoDto resultStoredUserInfoDto = userEntityToStoredDtoMapper.toDto(userEntity);
        assertThat(resultStoredUserInfoDto).isEqualTo(storedUserInfoDto);
    }

    @Test
    public void testUserEntityFromStoredDtoMapping() {
        final UserEntity resultUserEntity = userEntityToStoredDtoMapper.fromDto(storedUserInfoDto);

        userEntity.getRefreshToken().setId(null);
        assertThat(resultUserEntity).isEqualTo(userEntity);
    }

    @BeforeEach
    public void prepare() {

        final long id = 1L;
        final String nameStr = "u";
        final String usernameStr = "123@gmail.com";
        final String passwordStr = "p";
        final Set<RoleEntity> roleEntities = Set.of(RoleEntity.USER);
        final boolean active = true;
        final boolean nonLocked = true;
        final LocalDateTime creationDateTime = LocalDateTime.now().minusDays(3);
        final LocalDateTime lastVisitDateTime = LocalDateTime.now();
        final String refreshTokenStr = "1234567890";

        final Set<StoredRoleDto> roles = Set.of(StoredRoleDto.USER);

        userEntity = new UserEntity()
                .setId(id)
                .setName(nameStr)
                .setEmail(usernameStr)
                .setEncodedPassword(passwordStr)
                .setRoles(roleEntities)
                .setActive(active)
                .setNonLocked(nonLocked)
                .setCreationDateTime(creationDateTime)
                .setLastVisitDateTime(lastVisitDateTime);
        userEntity.setRefreshToken(new RefreshTokenEntity(
                userEntity.getId(),
                refreshTokenStr,
                userEntity
        ));

        storedUserInfoDto = new StoredUserInfoDto()
                .setId(id)
                .setName(nameStr)
                .setLoginInfo(new StoredUserLoginInfoDto()
                        .setUsername(new StoredUsernameDto(usernameStr))
                        .setEncodedPassword(new StoredEncodedPasswordDto(passwordStr))
                        .setRoles(roles))
                .setActiveDetails(new StoredActiveUserDetailsDto()
                        .setActive(active)
                        .setNonLocked(nonLocked)
                        .setCreationDateTime(creationDateTime)
                        .setLastVisitDateTime(lastVisitDateTime))
                .setRefreshToken(new StoredRefreshTokenDto(refreshTokenStr));
    }
}