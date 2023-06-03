package com.example.toyblog.config.handler;

import com.example.toyblog.dto.response.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RequiredArgsConstructor
public class LoginFailHandler implements AuthenticationFailureHandler {
    private final ObjectMapper objectMapper;
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.error("[인증오류] 아이디 혹은 비밀번호가 올바르지 않습니다!");
        log.info(request.getParameter("email"));
        log.info(request.getParameter("password"));
        ErrorResponse response1 = new ErrorResponse("400","[인증오류] 아이디 혹은 비밀번호가 올바르지 않습니다!");

        response.setCharacterEncoding(UTF_8.name());
        response.setContentType(APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        objectMapper.writeValue(response.getWriter(),response1);
    }
}
