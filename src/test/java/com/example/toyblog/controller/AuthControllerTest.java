package com.example.toyblog.controller;

import com.example.toyblog.domain.User;
import com.example.toyblog.dto.request.Login;
import com.example.toyblog.dto.request.Signup;
import com.example.toyblog.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

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