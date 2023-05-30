package com.example.toyblog.controller;

import com.example.toyblog.dto.request.CreatePost;
import com.example.toyblog.dto.request.EditPost;
import com.example.toyblog.dto.request.SearchOption;
import com.example.toyblog.dto.response.PostResponse;
import com.example.toyblog.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    @GetMapping("/posts")
    public List<PostResponse> getPost(@ModelAttribute SearchOption option){
        return postService.getList(option);
    }

    @PostMapping("/posts")
    public void post(@RequestBody @Validated CreatePost request){
        postService.write(request);
        //TO-DO : 아이디를 리턴해서 글 생성 시 만들어진 글로 리다이랙트
    }

    @GetMapping("/posts/{postId}")
    public PostResponse get(@PathVariable Long postId){
        PostResponse post = postService.get(postId);
        return post;
    }

    @PatchMapping("/posts/{postId}")
    public void edit(@PathVariable Long postId, @RequestBody @Valid EditPost request){
        postService.edit(postId,request);
    }

    @DeleteMapping("posts/{postId}")
    public void delete(@PathVariable Long postId){
        postService.delete(postId);
    }



}
