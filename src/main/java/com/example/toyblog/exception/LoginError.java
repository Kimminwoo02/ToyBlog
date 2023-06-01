package com.example.toyblog.exception;

public class LoginError extends ExceptionRole{
    private static final String MESSAGE = "아이디/비밀번호가 올바르지 않습니다.";

    public LoginError() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 0;
    }
}
