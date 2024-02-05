package com.pj.dasee.domain.profile.dto;

import com.pj.dasee.domain.profile.domain.Profile;
import com.pj.dasee.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
@Getter
public class ProfileCreateRequest {

    private final Long id;
    private final String name;

    private final Long userId;

    @Builder
    public ProfileCreateRequest(Long id, String name, Long userId) {
        this.id = id;
        this.name = name;
        this.userId = userId;
    }

    public Profile toEntity(User user) {
        return Profile.builder()
                .id(id)
                .name(name)
                .user(user)
                .build();
    }

    public Profile toEntity() {
        return Profile.builder()
                .id(id)
                .name(name)
                .build();
    }
}
