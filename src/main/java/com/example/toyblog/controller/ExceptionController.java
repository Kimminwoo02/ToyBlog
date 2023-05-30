package com.example.toyblog.controller;

import com.example.toyblog.dto.response.ErrorResponse;
import com.example.toyblog.exception.ExceptionRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class ExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ErrorResponse invalidRequestHandler(MethodArgumentNotValidException e){
        FieldError fieldError = e.getFieldError();

        ErrorResponse response = new ErrorResponse("400","잘못된 요청입니다.");


        response.addValidation(fieldError.getField(),fieldError.getDefaultMessage());

        return response;
    }

    @ResponseBody
    @ExceptionHandler(ExceptionRole.class)
    public ResponseEntity<ErrorResponse> ExceptionRole(ExceptionRole e){
        int statusCode = e.getStatusCode();
        ErrorResponse body = new ErrorResponse(String.valueOf(statusCode), e.getMessage());
        ResponseEntity<ErrorResponse> response = ResponseEntity.status(statusCode)
                .body(body);

        return response;
    }


}
