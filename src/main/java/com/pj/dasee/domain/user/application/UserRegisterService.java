package com.pj.dasee.domain.user.application;

import com.pj.dasee.domain.profile.dao.ProfileRepository;
import com.pj.dasee.domain.profile.domain.Profile;
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
    private final ProfileRepository profileRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void register(UserRegisterRequest userRegisterRequest) {
        User user = User.builder()
                .email(userRegisterRequest.getEmail())
                .nickname(userRegisterRequest.getNickname())
                .password(bCryptPasswordEncoder.encode(userRegisterRequest.getPassword()))
                .build();
        userRepository.save(user);

        Profile profile = Profile.builder()
                .name("익명")
                .user(user)
                .build();
        profileRepository.save(profile);
    }
}
