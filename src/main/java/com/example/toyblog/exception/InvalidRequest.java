package com.example.toyblog.exception;

public class InvalidRequest extends ExceptionRole{
    private final static String MESSAGE = "잘못된 요청입니다!";
    public InvalidRequest() {
        super(MESSAGE);
    }
    @Override
    public int getStatusCode() {
        return 400;
    }
}
