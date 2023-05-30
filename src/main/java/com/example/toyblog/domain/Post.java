package com.example.toyblog.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter private String title;
    @Lob // Db에 LongText로 저장
    @Setter private String content;

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
