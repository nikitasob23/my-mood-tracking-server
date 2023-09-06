package com.niksob.database_model.mapper.active;

import com.niksob.domain_model.dto.user.active.ActiveUserDetailsDto;
import com.niksob.domain_model.mapper.user.active.ActiveUserDetailsDtoMapper;
import com.niksob.domain_model.model.user.active.ActiveUserDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class ActiveUserDetailsDtoMapperTest {

    private final ActiveUserDetailsDtoMapper activeUserDetailsDtoMapper =
            Mappers.getMapper(ActiveUserDetailsDtoMapper.class);

    private ActiveUserDetails activeUserDetails;

    private ActiveUserDetailsDto activeUserDetailsDto;

    @Test
    public void testActiveUserDetailsToDtoMapping() {
        final ActiveUserDetailsDto resultActiveUserDetailsDto = activeUserDetailsDtoMapper.toDto(activeUserDetails);
        assertThat(resultActiveUserDetailsDto).isEqualTo(activeUserDetailsDto);
    }

    @Test
    public void testActiveUserDetailsFromDtoMapping() {
        final ActiveUserDetails resultActiveUserDetails = activeUserDetailsDtoMapper.fromDto(activeUserDetailsDto);
        assertThat(resultActiveUserDetails).isEqualTo(activeUserDetails);
    }

    @BeforeEach
    public void prepare() {

        final boolean activate = false;
        final boolean nonLocked = true;
        final LocalDateTime creationDateTime = LocalDateTime.now().minusDays(3);
        final LocalDateTime lastVisitDateTime = LocalDateTime.now();

        activeUserDetailsDto = new ActiveUserDetailsDto(
                activate,
                nonLocked,
                creationDateTime,
                lastVisitDateTime
        );

        activeUserDetails = new ActiveUserDetails(
                activate,
                nonLocked,
                creationDateTime,
                lastVisitDateTime
        );
    }
}
