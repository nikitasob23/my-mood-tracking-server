package com.niksob.domain_model.dto.user.active;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ActiveUserDetailsDto {
    private boolean active;
    @JsonProperty("non_locked")
    private boolean nonLocked;
    @JsonProperty("creation")
    private LocalDateTime creationDateTime;
    @JsonProperty("last_visit")
    private LocalDateTime lastVisitDateTime;
}

