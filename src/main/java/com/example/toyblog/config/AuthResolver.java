package com.example.toyblog.config;

import com.example.toyblog.config.data.UserSession;
import com.example.toyblog.domain.Session;
import com.example.toyblog.exception.Unauthorized;
import com.example.toyblog.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Optional;

@RequiredArgsConstructor
public class AuthResolver implements HandlerMethodArgumentResolver {
    private final SessionRepository sessionRepository;
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(UserSession.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String accessToken = webRequest.getHeader("Authorization");
        if(accessToken == null || accessToken.equals("")){
            throw new Unauthorized();
        }
        Session sessionOption = sessionRepository.findByAccessToken(accessToken)
                .orElseThrow(Unauthorized::new);

        return new UserSession(sessionOption.getUser().getId());
    }
}
