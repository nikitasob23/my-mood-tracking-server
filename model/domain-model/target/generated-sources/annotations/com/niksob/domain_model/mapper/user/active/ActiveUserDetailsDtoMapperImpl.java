package com.niksob.domain_model.mapper.user.active;

import com.niksob.domain_model.dto.user.active.ActiveUserDetailsDto;
import com.niksob.domain_model.model.user.active.ActiveUserDetails;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-06T23:23:48+0400",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.5 (Oracle Corporation)"
)
@Component
public class ActiveUserDetailsDtoMapperImpl implements ActiveUserDetailsDtoMapper {

    @Override
    public ActiveUserDetailsDto toDto(ActiveUserDetails activeUserDetails) {
        if ( activeUserDetails == null ) {
            return null;
        }

        ActiveUserDetailsDto activeUserDetailsDto = new ActiveUserDetailsDto();

        activeUserDetailsDto.setActive( activeUserDetails.isActive() );
        activeUserDetailsDto.setNonLocked( activeUserDetails.isNonLocked() );
        activeUserDetailsDto.setCreationDateTime( activeUserDetails.getCreationDateTime() );
        activeUserDetailsDto.setLastVisitDateTime( activeUserDetails.getLastVisitDateTime() );

        return activeUserDetailsDto;
    }

    @Override
    public ActiveUserDetails fromDto(ActiveUserDetailsDto activeUserDetailsDto) {
        if ( activeUserDetailsDto == null ) {
            return null;
        }

        ActiveUserDetails activeUserDetails = new ActiveUserDetails();

        activeUserDetails.setActive( activeUserDetailsDto.isActive() );
        activeUserDetails.setNonLocked( activeUserDetailsDto.isNonLocked() );
        activeUserDetails.setCreationDateTime( activeUserDetailsDto.getCreationDateTime() );
        activeUserDetails.setLastVisitDateTime( activeUserDetailsDto.getLastVisitDateTime() );

        return activeUserDetails;
    }
}
