package com.niksob.database_controller.controller;

import com.niksob.database_repository.entity.refresh_token.RefreshTokenEntity;
import com.niksob.database_repository.entity.user.RoleEntity;
import com.niksob.database_repository.entity.user.UserEntity;
import com.niksob.database_repository.mapper.user.UserEntityToStoredDtoMapper;
import com.niksob.database_repository.mapper.user.stored.UserInfoToStoredDtoMapper;
import com.niksob.database_repository.repository.user.UserRepository;
import com.niksob.domain_model.dto.user.UserInfoDto;
import com.niksob.domain_model.mapper.user.UserInfoDtoMapper;
import com.niksob.domain_model.model.user.UserInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    private UserInfo expectedUserInfo;
    private UserInfoDto userInfoDto;
    private final String usernameQueryParamName = "username";
    private final String usernameStr = "123@gmail.com";
    @Autowired
    private WebTestClient webClient;
    @Autowired
    private UserEntityToStoredDtoMapper userEntityToStoredDtoMapper;
    @Autowired
    private UserInfoToStoredDtoMapper userInfoToStoredDtoMapper;
    @Autowired
    private UserInfoDtoMapper userInfoDtoMapper;
    @Autowired
    @Qualifier("loading_user_by_username_uri")
    private String loadingUserUri;
    @Autowired
    @Qualifier("saving_user_uri")
    private String savingUserUri;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testLoadingUserInfo() {
        final UserInfo loadedUserInfo = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(loadingUserUri)
                        .queryParam(usernameQueryParamName, usernameStr)
                        .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .returnResult(UserInfoDto.class)
                .getResponseBody()
                .map(userInfoDtoMapper::fromDto)
                .blockLast();

        assertThat(loadedUserInfo).isEqualTo(expectedUserInfo);
    }

    @Test
    public void testSavingUserInfo() {
        webClient.post()
                .uri(savingUserUri)
                .body(Mono.just(userInfoDto), UserInfoDto.class)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
    }

    @BeforeEach
    public void prepare() {

        final long id = 1L;
        final String name = "Ivan";
        final String passwordStr = "TEST_ENCODED_PASSWORD";
        final Set<RoleEntity> roles = Set.of(RoleEntity.USER);

        final UserEntity userEntity = new UserEntity()
                .setId(id)
                .setEmail(usernameStr)
                .setName(name)
                .setEncodedPassword(passwordStr)
                .setRoles(roles)
                .setActive(true)
                .setNonLocked(true)
                .setCreationDateTime(LocalDateTime.now().minusDays(3))
                .setLastVisitDateTime(LocalDateTime.now());
        userEntity.setRefreshToken(new RefreshTokenEntity(id, "1234567890", userEntity));

        expectedUserInfo = Stream.of(userEntity)
                .map(userEntityToStoredDtoMapper::toDto)
                .map(userInfoToStoredDtoMapper::fromDto)
                .findFirst().get();

        userInfoDto =  userInfoDtoMapper.toDto(expectedUserInfo);

        mockLoadingUserInfo(userEntity);

        mockSavingUserInfo(userEntity);
    }

    private void mockLoadingUserInfo(UserEntity userEntity) {
        Mockito.when(userRepository.findByEmail(userEntity.getEmail())).thenReturn(userEntity);
    }

    private void mockSavingUserInfo(UserEntity userEntity) {
        Mockito.when(userRepository.save(userEntity)).thenReturn(userEntity);
    }
}
