package com.niksob.database_repository.model.user.active;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class StoredActiveUserDetailsDto {
    private boolean active;
    private boolean nonLocked;
    private LocalDateTime creationDateTime;
    private LocalDateTime lastVisitDateTime;
}
