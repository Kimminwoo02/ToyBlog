package com.example.toyblog.service;

import com.example.toyblog.domain.User;
import com.example.toyblog.dto.request.Login;
import com.example.toyblog.dto.request.Signup;
import com.example.toyblog.exception.AlreadyExistEmail;
import com.example.toyblog.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.rmi.AlreadyBoundException;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class AuthServiceTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthService authService;

    @Test
    @DisplayName("회원 가입성공")
    void 회원가입(){
        Signup signup = new Signup("미누","asdfasdf@naver.com","1234");

        authService.signup(signup);

        Assertions.assertEquals(1,userRepository.count());
        User user = userRepository.findAll().iterator().next();
        assertEquals("미누",user.getName());
        assertEquals("1234",user.getPassword());
        assertEquals("asdfasdf@naver.com",user.getEmail());
    }

    @Test
    @DisplayName("회원 가입실패")
    void 회원가입시_중복이메일(){
        User user = new User("미누", "asdfasdf@naver.com", "1234");
        userRepository.save(user);
        Signup signup1 = new Signup("미누","asdfasdf@naver.com","1234");


        assertThrows(AlreadyExistEmail.class, () -> authService.signup(signup1));

    }
}