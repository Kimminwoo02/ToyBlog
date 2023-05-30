package com.example.toyblog.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class PostResponse {

    private Long id;
    private String title;
    private String content;

    public PostResponse(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
    public void Change(String title, String content){
        this.title = title;
        this.content = content;
    }
}
