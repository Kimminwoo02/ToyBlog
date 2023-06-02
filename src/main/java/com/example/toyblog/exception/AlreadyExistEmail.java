package com.example.toyblog.exception;

public class AlreadyExistEmail extends ExceptionRole{
    private static final String MESSAGE = "이미 가입된 메일입니다.";

    public AlreadyExistEmail() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }
}
