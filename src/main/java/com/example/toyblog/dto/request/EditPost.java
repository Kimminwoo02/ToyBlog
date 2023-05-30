package com.example.toyblog.dto.request;

import jdk.jfr.StackTrace;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EditPost {
    private String title;
    private String content;

    public EditPost(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public EditPost() {
    }
}
