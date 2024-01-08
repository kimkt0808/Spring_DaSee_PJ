package com.pj.dasee.domain.user.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

/*
 * User Validation Logic
*/
@RequiredArgsConstructor
@Service
public class UserCheckService {

    @Transactional(readOnly = true)
    public Map<String, String> validateUserRegisterRequest(Errors errors) {
        Map<String, String> validatorError = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorError.put(validKeyName, error.getDefaultMessage());
        }
        return validatorError;
    }
}
