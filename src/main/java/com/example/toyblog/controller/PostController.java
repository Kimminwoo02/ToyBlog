package com.example.toyblog.controller;

import com.example.toyblog.dto.request.CreatePost;
import com.example.toyblog.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    @GetMapping("/posts")
    public String getPost(){
        return "hell";
    }

    @PostMapping("/posts")
    public Map<String,String> post(@RequestBody @Validated CreatePost request){
        postService.write(request);
        return  Map.of();
    }



}
