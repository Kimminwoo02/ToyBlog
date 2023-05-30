package com.example.toyblog.controller;

import com.example.toyblog.domain.Post;
import com.example.toyblog.dto.request.CreatePost;
import com.example.toyblog.repository.PostRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PostRepository postRepository;
    @Test
    void test1() throws Exception {
     mockMvc.perform(get("/posts"))
             .andExpect(status().isOk())
             .andExpect(content().string("hell"))
             .andDo(print());
    }

    @Test
    @DisplayName("post 요청 시 title 값은 필수이다. ")
    void 타이틀검증() throws Exception {
        mockMvc.perform(post("/posts")
                        .contentType(APPLICATION_JSON)
                        .content("{\"title\": \"\", \"content\": \"내용입니다.\"}")
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
                .andExpect(jsonPath("$.validation.title").value("타이틀을 입력해주세요"))
                .andDo(print());
    }


    @Test
    @DisplayName("post 요청 시 DB에 값이 저장된다. ")
    void 포스트요청() throws Exception {
        //given
        CreatePost request = new CreatePost("제목입니다.","내용입니다.");

        String json = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/posts")
                        .contentType(APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("타이틀이 Null인 경우")
    void title값이_널이다() throws Exception {
        //given
        CreatePost request = new CreatePost(null,"내용입니다.");

        String json = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/posts")
                        .contentType(APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    @DisplayName("글 한개 조회")
    void 글_1개_조회() throws Exception {
        //given
        Post post = new Post("제목1","내용1");
        postRepository.save(post);


        mockMvc.perform(get("/posts/{postId}",post.getId())
                        .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(post.getId()))
                .andExpect(jsonPath("$.title").value("제목1"))
                .andExpect(jsonPath("$.content").value("내용1"))
                .andDo(print());
    }


}