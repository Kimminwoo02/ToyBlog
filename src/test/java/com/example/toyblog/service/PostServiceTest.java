package com.example.toyblog.service;

import com.example.toyblog.domain.Post;
import com.example.toyblog.dto.request.CreatePost;
import com.example.toyblog.dto.response.PostResponse;
import com.example.toyblog.repository.PostRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class PostServiceTest {
    @Autowired
    private PostService postService;
    @Autowired
    private PostRepository postRepository;


    @Test
    @DisplayName("글 작성")
    void 글작성(){
        CreatePost post = new CreatePost("제목입니다.","내용입니다.");

        postService.write(post);

        Assertions.assertEquals(1L,postRepository.count());

        Post findpost = postRepository.findAll().get(0);
        assertEquals(findpost.getTitle(),"제목입니다.");
        assertEquals(findpost.getContent(),"내용입니다.");
    }

    @Test
    @DisplayName("글 한개 조회")
    void 글_한개_조회(){
        Post post = new Post("제목1","내용1");
        postRepository.save(post);

        PostResponse findPost = postService.get(post.getId());

        assertNotNull(findPost);
        assertEquals("제목1",post.getTitle());
        assertEquals("내용1",post.getContent());
    }

    @Test
    @DisplayName("바뀐 글 조회")
    void 수정_조회(){
        Post post = new Post("제목1","내용1");
        postRepository.save(post);

        PostResponse findPost = postService.get(post.getId());
        findPost.Change("제목2","내용2");

        assertEquals("제목2",findPost.getTitle());
        assertEquals("내용2",findPost.getContent());
    }



}