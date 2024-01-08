package com.pj.dasee.domain.user.validation;

import com.pj.dasee.domain.user.dao.UserRepository;
import com.pj.dasee.domain.user.dto.UserRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@RequiredArgsConstructor
@Component
public class CheckNicknameValidator extends AbstractValidator<UserRegisterRequest> {

    private final UserRepository userRepository;

    @Override
    protected void doValidate(UserRegisterRequest userRegisterRequest, Errors errors) {
        if (userRepository.existsByNickname(userRegisterRequest.toEntity().getNickname())) {
            errors.rejectValue("nickname", "닉네임 중복 오류", "이미 사용중인 닉네임입니다.");
        }
    }
}
