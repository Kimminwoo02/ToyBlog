package com.example.toyblog.dto.response;

import com.example.toyblog.domain.BaseEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
public class PostResponse {

    private Long id;
    private String title;
    private String content;

    private String createdAt;
    private String modifiedAt;

    public PostResponse(Long id, String title, String content, String createdAt, String modifiedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
    public void Change(String title, String content){
        this.title = title;
        this.content = content;
    }
}
