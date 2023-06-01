package com.example.toyblog.controller;

import com.example.toyblog.domain.User;
import com.example.toyblog.dto.request.Login;
import com.example.toyblog.dto.response.SessionResponse;
import com.example.toyblog.exception.LoginError;
import com.example.toyblog.repository.UserRepository;
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

    private final AuthService authService;

    @PostMapping("/auth/login")
    public SessionResponse login(@RequestBody Login login){
        String accessToken = authService.signIn(login);
        return new SessionResponse(accessToken);
    }
}
