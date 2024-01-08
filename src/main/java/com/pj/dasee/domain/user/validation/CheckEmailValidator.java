package com.pj.dasee.domain.user.validation;

import com.pj.dasee.domain.user.dao.UserRepository;
import com.pj.dasee.domain.user.dto.UserRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@RequiredArgsConstructor
@Component
public class CheckEmailValidator extends AbstractValidator<UserRegisterRequest> {

    private final UserRepository userRepository;

    @Override
    protected void doValidate(UserRegisterRequest userRegisterRequest, Errors errors) {
        if (userRepository.existsByEmail(userRegisterRequest.toEntity().getEmail())) {
            errors.rejectValue("email", "아이디 중복 오류", "이미 사용중인 이메일입니다.");
        }
    }
}
