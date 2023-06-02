package com.example.toyblog.dto.request;

import lombok.Data;

@Data
public class Signup {
    private String email;
    private String password;
    private String name;

    public Signup() {
    }

    public Signup(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
