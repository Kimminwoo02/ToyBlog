package com.example.toyblog.config;

import com.example.toyblog.config.handler.LoginFailHandler;
import com.example.toyblog.domain.User;
import com.example.toyblog.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class SecurityConfig {
    private final ObjectMapper objectMapper;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return new WebSecurityCustomizer() {
            @Override
            public void customize(WebSecurity web) {
                web.ignoring().requestMatchers("/favicon.ico","/error");
            }
        };
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .authorizeHttpRequests((auth)->auth
                        .requestMatchers("/auth/login").permitAll()
                        .requestMatchers("/auth/signup").permitAll()
                        .requestMatchers("/user").hasRole("USER")
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .anyRequest().authenticated())
                        .addFilterAt(getAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)

//                .formLogin((auth)->auth
//                        .usernameParameter("email")
//                        .passwordParameter("password")
//                        .loginPage("/auth/login")
//                        .loginProcessingUrl("/auth/login")
//                        .defaultSuccessUrl("/")
//
//                        .failureHandler(new LoginFailHandler(objectMapper)))
//                        .exceptionHandling(e->{
//                            e.accessDeniedHandler(new Http403Handler(objectMapper));
//                            e.authenticationEntryPoint(new Http401Handler(objectMapper));
//                })
                        .rememberMe(rm-> rm.rememberMeParameter("remember")
                        .alwaysRemember(false)
                        .tokenValiditySeconds(2592000))
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }

    protected NamePasswordJson getAuthenticationFilter() {
        NamePasswordJson authFilter = new NamePasswordJson(objectMapper);
        try{
            authFilter.setFilterProcessesUrl("/auth/login");
            authFilter.setUsernameParameter("email");
            authFilter.setPasswordParameter("password");
            authFilter.setAuthenticationManager(new AuthenticationManager() {
                @Override
                public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                    return null;
                }
            });
            authFilter.setAuthenticationFailureHandler(new LoginFailHandler(objectMapper));
            }catch (Exception e){
            e.printStackTrace();
        }
        return authFilter;
        }


    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository){
        return username -> {
            User user = userRepository.findByEmail(username)
                    .orElseThrow(()->new UsernameNotFoundException(username +"을 찾을 수 없습니다."));
            return new UserPrincipal(user);
        };

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

}
