package com.pj.dasee.domain.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/login")
    public String login() {
        return "User/login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "/User/signup";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }
}
