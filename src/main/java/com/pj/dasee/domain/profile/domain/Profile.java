package com.pj.dasee.domain.profile.domain;

import com.pj.dasee.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Getter
@Entity
@Table(name = "profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Profile(Long id, String name, User user) {
        this.id = id;
        this.name = name;
        this.user = user;
    }

    public void updateProfile(String name) {
        this.name = name;
    }
}
