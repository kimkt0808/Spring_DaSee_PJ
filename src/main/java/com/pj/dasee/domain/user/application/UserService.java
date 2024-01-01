package com.pj.dasee.domain.user.application;

import com.pj.dasee.domain.user.dao.UserRepository;
import com.pj.dasee.domain.user.domain.User;
import com.pj.dasee.domain.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void save(UserDto dto) {
        userRepository.save(User.builder()
                .email(dto.getEmail())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .build());
    }
}
