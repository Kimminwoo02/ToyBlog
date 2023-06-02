package com.example.toyblog.controller;

import com.example.toyblog.domain.Session;
import com.example.toyblog.domain.User;
import com.example.toyblog.dto.request.Login;
import com.example.toyblog.dto.request.Signup;
import com.example.toyblog.repository.SessionRepository;
import com.example.toyblog.repository.UserRepository;
import com.example.toyblog.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Test
    @DisplayName("로그인 성공")
    void 타이틀검증() throws Exception {
        userRepository.save(new User("minu","kmw950701@naver.com","1234"));

        Login login = new Login("kmw950701@naver.com","1234");

        String json = objectMapper.writeValueAsString(login);

        mockMvc.perform(post("/auth/login")
                        .contentType(APPLICATION_JSON)
                        .content("{\"title\": \"\", \"content\": \"내용입니다.\"}")
                        .content(json))
                .andExpect(status().isOk())
                .andDo(print());
    }


    @Test
    @DisplayName("로그인 성공 후 세션 생성")
    void 세션검증() throws Exception {
        User user = new User("minu","kmw950701@naver.com","1234");
        userRepository.save(user);

        Login login = new Login("kmw950701@naver.com","1234");

        String json = objectMapper.writeValueAsString(login);

        mockMvc.perform(post("/auth/login")
                        .contentType(APPLICATION_JSON)
                        .content("{\"title\": \"\", \"content\": \"내용입니다.\"}")
                        .content(json))
                .andExpect(status().isOk())
                .andDo(print());


        assertEquals(1L,user.getSessions().size());
    }


    @Test
    @DisplayName("로그인 성공 후 세션 응답")
    void 세션응답() throws Exception {
        User user = new User("minu","kmw950701@naver.com","1234");
        userRepository.save(user);

        Login login = new Login("kmw950701@naver.com","1234");

        String json = objectMapper.writeValueAsString(login);

        mockMvc.perform(post("/auth/login")
                        .contentType(APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken", Matchers.notNullValue()))
                .andDo(print());


        assertEquals(1L,user.getSessions().size());
    }

    @Test
    @DisplayName("로그인 후 권한이 필요한 페이지에 접속")
    void 세션_및_페이지접속() throws Exception{
        User user = new User("minu","kmw950701@naver.com","1234");

        Session session = user.addSession();
        userRepository.save(user);

        mockMvc.perform(get("/test")
                        .header("Authorization",session.getAccessToken())
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("로그인 후 검증되지 않은 세션값으로 요청 시 권한이 필요한 페이지에 접속할 수 없다.")
    void 인가되지_않은_사용자_페이지접속() throws Exception{
        User user = new User("minu","kmw950701@naver.com","1234");

        Session session = user.addSession();
        userRepository.save(user);

        mockMvc.perform(get("/test")
                        .header("Authorization",session.getAccessToken()+"-o")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    @DisplayName("회원가입")
    void 회원가입() throws Exception{
        Signup signup1 = new Signup("미누","asdfasdf@naver.com","1234");

        mockMvc.perform(post("/auth/signup")
                        .content(objectMapper.writeValueAsString(signup1))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
}