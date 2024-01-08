package com.pj.dasee.domain.user.application;

import com.pj.dasee.domain.user.dao.UserRepository;
import com.pj.dasee.domain.user.domain.User;
import com.pj.dasee.domain.user.dto.UserRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
 * User Business Logic
*/
@RequiredArgsConstructor
@Service
public class UserRegisterService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void register(UserRegisterRequest userRegisterRequest) {
        userRepository.save(User.builder()
                .email(userRegisterRequest.getEmail())
                .nickname(userRegisterRequest.getNickname())
                .password(bCryptPasswordEncoder.encode(userRegisterRequest.getPassword()))
                .build());
    }
}
