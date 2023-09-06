package com.niksob.domain_model.mapper.user;

import com.niksob.domain_model.dto.token.refresh.RefreshTokenDto;
import com.niksob.domain_model.dto.user.UserInfoDto;
import com.niksob.domain_model.mapper.user.active.ActiveUserDetailsDtoMapper;
import com.niksob.domain_model.mapper.user.login.UserLoginInfoDtoMapper;
import com.niksob.domain_model.model.token.refresh.RefreshToken;
import com.niksob.domain_model.model.user.UserInfo;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-06T23:23:48+0400",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.5 (Oracle Corporation)"
)
@Component
public class UserInfoDtoMapperImpl implements UserInfoDtoMapper {

    @Autowired
    private UserLoginInfoDtoMapper userLoginInfoDtoMapper;
    @Autowired
    private ActiveUserDetailsDtoMapper activeUserDetailsDtoMapper;

    @Override
    public UserInfoDto toDto(UserInfo userInfo) {
        if ( userInfo == null ) {
            return null;
        }

        UserInfoDto userInfoDto = new UserInfoDto();

        userInfoDto.setName( userInfo.getName() );
        userInfoDto.setLoginInfo( userLoginInfoDtoMapper.toDto( userInfo.getLoginInfo() ) );
        userInfoDto.setActiveDetails( activeUserDetailsDtoMapper.toDto( userInfo.getActiveDetails() ) );
        userInfoDto.setRefreshToken( refreshTokenToRefreshTokenDto( userInfo.getRefreshToken() ) );

        return userInfoDto;
    }

    @Override
    public UserInfo fromDto(UserInfoDto userInfoDto) {
        if ( userInfoDto == null ) {
            return null;
        }

        UserInfo userInfo = new UserInfo();

        userInfo.setName( userInfoDto.getName() );
        userInfo.setLoginInfo( userLoginInfoDtoMapper.fromDto( userInfoDto.getLoginInfo() ) );
        userInfo.setActiveDetails( activeUserDetailsDtoMapper.fromDto( userInfoDto.getActiveDetails() ) );
        userInfo.setRefreshToken( refreshTokenDtoToRefreshToken( userInfoDto.getRefreshToken() ) );

        return userInfo;
    }

    protected RefreshTokenDto refreshTokenToRefreshTokenDto(RefreshToken refreshToken) {
        if ( refreshToken == null ) {
            return null;
        }

        RefreshTokenDto refreshTokenDto = new RefreshTokenDto();

        refreshTokenDto.setValue( refreshToken.getValue() );

        return refreshTokenDto;
    }

    protected RefreshToken refreshTokenDtoToRefreshToken(RefreshTokenDto refreshTokenDto) {
        if ( refreshTokenDto == null ) {
            return null;
        }

        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setValue( refreshTokenDto.getValue() );

        return refreshToken;
    }
}
