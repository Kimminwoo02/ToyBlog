package com.example.toyblog.service;

import com.example.toyblog.crypto.PasswordEncoder;
import com.example.toyblog.domain.Session;
import com.example.toyblog.domain.User;
import com.example.toyblog.dto.request.Login;
import com.example.toyblog.dto.request.Signup;
import com.example.toyblog.exception.AlreadyExistEmail;
import com.example.toyblog.exception.InvalidRequest;
import com.example.toyblog.exception.LoginError;
import com.example.toyblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    @Transactional
    public Long signIn(Login login){

        User user = userRepository.findByEmail(login.getEmail())
                .orElseThrow(LoginError::new);
        PasswordEncoder encoder = new PasswordEncoder();
        boolean matches = encoder.matches(login.getPassword(), user.getPassword());
        if (!matches){
            throw new LoginError();
        }

        return user.getId();
    }

    public void signup(Signup signUp) {
        Optional<User> byEmail = userRepository.findByEmail(signUp.getEmail());
        if(byEmail.isPresent()){
            throw new AlreadyExistEmail();
        }
        PasswordEncoder encoder = new PasswordEncoder();
        String encryptedPassword = encoder.encrypt(signUp.getPassword());

        User user = new User(signUp.getName(), signUp.getEmail(), encryptedPassword);

        userRepository.save(user);
    }
}
