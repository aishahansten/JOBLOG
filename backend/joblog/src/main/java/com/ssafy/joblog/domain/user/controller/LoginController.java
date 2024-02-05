package com.ssafy.joblog.domain.user.controller;

import com.ssafy.joblog.domain.user.entity.User;
import com.ssafy.joblog.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/test/oauth")
    public @ResponseBody String testLogin(Authentication authentication) {
        System.out.println("/test/oauth=================");
        User oAuth2User = (User) authentication.getPrincipal();
        System.out.println("oauth2User : " + oAuth2User.getUsername());
        return "OAuth 세션 정보 확인하기";
    }

    @GetMapping("")
    public String index() {
        return "index.html";
    }

    @GetMapping("/applicant")
    public String applicant() {
        return "applicant.html";
    }

    @GetMapping("/worker")
    public String worker() {
        return "worker.html";
    }

    @GetMapping("/chatter")
    public String chatter() {
        return "chatter.html";
    }

}
