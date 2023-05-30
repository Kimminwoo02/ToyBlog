package com.example.toyblog.service;

import com.example.toyblog.domain.Post;
import com.example.toyblog.dto.request.CreatePost;
import com.example.toyblog.dto.request.EditPost;
import com.example.toyblog.dto.request.SearchOption;
import com.example.toyblog.dto.response.PostResponse;
import com.example.toyblog.repository.PostRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    @Test
    @DisplayName("페이지 조회")
    void 글_조회(){
        List<Post> requestPosts = IntStream.range(0,20)
                .mapToObj(i-> new Post("제목"+i,"내용"+i))
                .collect(Collectors.toList());

        postRepository.saveAll(requestPosts);

        SearchOption option = new SearchOption(1,10);


        List<PostResponse> posts = postService.getList(option);

        assertEquals(10L,posts.size());
        assertEquals("제목19",posts.get(0).getTitle());

    }

    @Test
    @DisplayName("글 제목 수정")
    void 글_제목_수정(){
        Post post = new Post("제목입니다!", "내용입니다!");
        postRepository.save(post);

        EditPost changedPost = new EditPost("바뀐 제목입니다", "내용입니다!");
        postService.edit(post.getId(),changedPost);

        postRepository.findById(post.getId())
                        .orElseThrow(()->new RuntimeException("글이 존재하지 않습니다. id=" + post.getId()));

        assertEquals("바뀐 제목입니다",post.getTitle());

    }

    @Test
    @DisplayName("글 내용 수정")
    void 글_내용_수정(){
        Post post = new Post("제목입니다!", "내용입니다!");
        postRepository.save(post);

        EditPost changedPost = new EditPost("제목입니다!", "바뀐 내용입니다.");
        postService.edit(post.getId(),changedPost);

        postRepository.findById(post.getId())
                .orElseThrow(()->new RuntimeException("글이 존재하지 않습니다. id=" + post.getId()));

        assertEquals("바뀐 내용입니다.",post.getContent());

    }

    @Test
    @DisplayName("글 내용 수정")
    void 글_내용_수정_ver2(){
        Post post = new Post("제목입니다!", "내용입니다!");
        postRepository.save(post);

        EditPost changedPost = new EditPost(null, "바뀐 내용입니다.");
        postService.edit(post.getId(),changedPost);

        postRepository.findById(post.getId())
                .orElseThrow(()->new RuntimeException("글이 존재하지 않습니다. id=" + post.getId()));

        assertEquals("제목입니다!",post.getTitle());
        assertEquals("바뀐 내용입니다.",post.getContent());

    }

    @Test
    @DisplayName("글 내용 수정")
    void 글_삭제(){
        Post post = new Post("제목입니다!", "내용입니다!");
        postRepository.save(post);

        postRepository.findById(post.getId())
                .orElseThrow(()->new RuntimeException("글이 존재하지 않습니다. id=" + post.getId()));

        postService.delete(post.getId());

        assertEquals(0,postRepository.count());

    }





}