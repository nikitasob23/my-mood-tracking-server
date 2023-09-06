package com.niksob.database_repository.entity.refresh_token;

import com.niksob.database_repository.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity
@Table(name = "refresh_tokens")
@Data
@EqualsAndHashCode(exclude = "user")
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class RefreshTokenEntity {

    @Id
    @Column(name = "user_id")
    private Long id;

    private String token;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private UserEntity user;
}
