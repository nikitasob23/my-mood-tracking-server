package com.niksob.database_repository.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.niksob.database_repository.entity.refresh_token.RefreshTokenEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Set;


@Entity
@Table(name = "usrs")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    private String name;

    @JsonIgnore
    @Column(name = "password")
    private String encodedPassword;

    @ElementCollection(targetClass = RoleEntity.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Set<RoleEntity> roles;

    private boolean active = false;

    private boolean nonLocked = true;

    @Column(name = "creation")
    private LocalDateTime creationDateTime;

    @Column(name = "last_visit")
    private LocalDateTime lastVisitDateTime;


    @OneToOne(mappedBy = "user", fetch = FetchType.EAGER)
    @JsonIgnore
    private RefreshTokenEntity refreshToken;

    public void activate() {
        setActive(true);
    }
}
