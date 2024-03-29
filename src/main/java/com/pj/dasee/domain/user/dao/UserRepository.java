package com.pj.dasee.domain.user.dao;

import com.pj.dasee.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    boolean existsByNickname(String username);
    boolean existsByEmail(String email);
}
