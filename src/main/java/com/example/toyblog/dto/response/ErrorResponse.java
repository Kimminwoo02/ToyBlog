package com.example.toyblog.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Getter
public class ErrorResponse {
    private final String code;
    private final String message;
    private final Map<String,String> validation = new HashMap<>();

    public void addValidation(String fieldName,String errorMessage){
        this.validation.put(fieldName,errorMessage);
    }
}
