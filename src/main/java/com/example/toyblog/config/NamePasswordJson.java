package com.example.toyblog.config;

import com.example.toyblog.dto.request.Login;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class NamePasswordJson extends UsernamePasswordAuthenticationFilter {
    private final ObjectMapper objectMapper;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        UsernamePasswordAuthenticationToken authenticationToken;
        if (request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)){
            try{
                Login login = objectMapper.readValue(
                        request.getReader().lines().collect(Collectors.joining()),Login.class);
                authenticationToken = new UsernamePasswordAuthenticationToken(login.getEmail(),login.getPassword());
            }   catch (IOException e) {
                e.printStackTrace();
                throw new AuthenticationServiceException("Request Content-Type");
            }
        }else {
            String email = obtainUsername(request);
            String password = obtainPassword(request);
            authenticationToken = new UsernamePasswordAuthenticationToken(email,password);
        }
        this.setDetails(request,authenticationToken);
        return this.getAuthenticationManager().authenticate(authenticationToken);
    }
}
