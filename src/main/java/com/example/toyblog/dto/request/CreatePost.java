package com.example.toyblog.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreatePost {
    @NotBlank(message = "타이틀을 입력해주세요")
    private String title;
    @NotBlank(message = "내용을 입력해주세요")
    private String content;

    public CreatePost() {
    }

    public CreatePost(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
