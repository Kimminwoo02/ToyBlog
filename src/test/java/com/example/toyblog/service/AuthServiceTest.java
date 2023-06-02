package com.example.toyblog.service;

import com.example.toyblog.crypto.PasswordEncoder;
import com.example.toyblog.domain.User;
import com.example.toyblog.dto.request.Login;
import com.example.toyblog.dto.request.Signup;
import com.example.toyblog.exception.AlreadyExistEmail;
import com.example.toyblog.exception.LoginError;
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
        PasswordEncoder encoder = new PasswordEncoder();
        Signup signup = new Signup("미누","asdfasdf@naver.com","1234");

        authService.signup(signup);

        assertEquals(1,userRepository.count());
        User user = userRepository.findAll().iterator().next();
        assertEquals("미누",user.getName());
        assertTrue(encoder.matches("1234",user.getPassword()));
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


    @Test
    @DisplayName("로그인 성공")
    void 로그인_성공(){
        PasswordEncoder encoder = new PasswordEncoder();
        String encrypt = encoder.encrypt("1234");

        User user = new User("미누", "asdfasdf@naver.com", encrypt);
        userRepository.save(user);

        Login login = new Login("asdfasdf@naver.com","1234");
        authService.signIn(login);

        Long userId = authService.signIn(login);

        assertNotNull(userId);
    }

    @Test
    @DisplayName("로그인_실패_비밀번호오류")
    void 로그인_실패_비밀번호오류(){
        PasswordEncoder encoder = new PasswordEncoder();
        String encrypt = encoder.encrypt("1234");

        Signup signup1 = new Signup("미누","asdfasdf@naver.com",encrypt);
        authService.signup(signup1);

        Login login = new Login("asdfasdf@naver.com","5678");

        Assertions.assertThrows(LoginError.class,()->authService.signIn(login));
    }


}