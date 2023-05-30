package com.example.toyblog.controller;

import com.example.toyblog.dto.request.CreatePost;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class PostController {
    @GetMapping("/posts")
    public String getPost(){
        return "hell";
    }

    @PostMapping("/posts")
    public String post(@RequestBody @Validated CreatePost createPost){
        String title = createPost.getTitle();
        String content = createPost.getContent();


        return "hell";

    }



}
