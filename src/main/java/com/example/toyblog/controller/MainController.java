package com.example.toyblog.controller;

import com.example.toyblog.config.UserPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping("/")
    public String main(){
        return "home";
    }

    @GetMapping("/user")
    public String user(@AuthenticationPrincipal UserPrincipal userPrincipal){

        return "사용자 페이지입니다.";
    }

    @GetMapping("/admin")
    public String admin(){
        return "관리자 페이지입니다.";
    }
}
