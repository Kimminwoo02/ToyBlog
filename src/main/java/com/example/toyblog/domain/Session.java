package com.example.toyblog.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

import static java.util.UUID.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accessToken;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Session(User user) {
        this.accessToken = randomUUID().toString();
        this.user = user;
    }

}
