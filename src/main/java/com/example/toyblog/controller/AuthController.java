package com.example.toyblog.controller;

import com.example.toyblog.config.AppConfig;

import com.example.toyblog.dto.request.Signup;
import com.example.toyblog.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AppConfig appConfig;
    private final AuthService authService;

    @PostMapping("/auth/signup")
    public void signup(@RequestBody Signup signUp){
        authService.signup(signUp);
    }

}
