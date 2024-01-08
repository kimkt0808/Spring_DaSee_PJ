package com.pj.dasee.domain.user.domain;

import com.pj.dasee.global.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "users")
public class User extends BaseTimeEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Builder
    public User(String nickname, String email, String password, String auth) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }

    /* 권한 목록 */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    /* password */
    @Override
    public String getPassword() {
        return password;
    }

    /* username */
    @Override
    public String getUsername() {
        return email;
    }

    /* 계정 만료 여부 */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /* 계정 잠금 여부 */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /* 패스워드 만료 여부 */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /* 계정 사용 가능 여부 */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
