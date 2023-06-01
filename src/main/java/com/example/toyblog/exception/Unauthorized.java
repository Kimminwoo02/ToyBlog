package com.example.toyblog.exception;

public class Unauthorized extends ExceptionRole{

    private final static String MESSAGE="허가되지 않은 사용자입니다.";

    public Unauthorized() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 401;
    }
}
