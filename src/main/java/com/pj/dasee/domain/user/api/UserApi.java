package com.pj.dasee.domain.user.api;

import com.pj.dasee.domain.user.application.UserCheckService;
import com.pj.dasee.domain.user.application.UserRegisterService;
import com.pj.dasee.domain.user.dto.UserLoginRequest;
import com.pj.dasee.domain.user.dto.UserRegisterRequest;
import com.pj.dasee.domain.user.validation.CheckEmailValidator;
import com.pj.dasee.domain.user.validation.CheckNicknameValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Log4j2
@RequiredArgsConstructor
@Controller
public class UserApi {

    private final UserRegisterService userRegisterService;
    private final UserCheckService userCheckService;

    private final AuthenticationManager authenticationManager;

    private final CheckEmailValidator checkEmailValidator;
    private final CheckNicknameValidator checkNicknameValidator;

    /* 커스텀 유효성 검증 */
    @InitBinder
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(checkEmailValidator);
        binder.addValidators(checkNicknameValidator);
    }

    /* Errors는 반드시 Request 객체 바로 뒤에 위치(두 개의 객체인 경우 각각 배치) */
    @PostMapping("/register")
    public String register(@Valid UserRegisterRequest userRegisterRequest, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("userRegisterRequest", userRegisterRequest);

            Map<String, String> validatorError = userCheckService.validateUserRegisterRequest(errors);
            for (String key : validatorError.keySet()) {
                model.addAttribute(key, validatorError.get(key));
            }

            return "User/registerForm";
        }

        userRegisterService.register(userRegisterRequest);
        return "redirect:/loginForm";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserLoginRequest userLoginRequest) {
        try {
            String email = userLoginRequest.getEmail();
            String password = userLoginRequest.getPassword();

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            email,
                            password
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return "redirect:/";
        } catch (Exception e) {
            return "redirect:/login=error";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response,
                SecurityContextHolder.getContext().getAuthentication());

        return "redirect:/loginForm";
    }
}
