package com.pj.dasee.domain.user.validation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Slf4j
public abstract class AbstractValidator<T> implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @SuppressWarnings("unchecked") /* 컴파일러 경고 무시 */
    @Override
    public void validate(Object target, Errors errors) {
        try {
            doValidate((T) target, errors);
        } catch (RuntimeException e) {
            log.error("중복 검증 에러", e);
            throw e;
        }
    }

    protected abstract void doValidate(final T userRegisterRequest, final Errors errors);
}
