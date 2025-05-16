package com.reactive.appreactive.service;

import com.reactive.appreactive.dto.ApiResponse;
import com.reactive.appreactive.dto.LoginDTO;
import com.reactive.appreactive.dto.SignUpDTO;
import com.reactive.appreactive.entity.User;
import com.reactive.appreactive.repository.UserRepository;
import com.reactive.appreactive.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    public Mono<ApiResponse<Boolean>> signUp(SignUpDTO signUpDTO) {
        if (!Objects.equals(signUpDTO.getPassword(), signUpDTO.getPrePassword()))
            throw new IllegalArgumentException("Passwords do not match");

        return userRepository.existsByEmail(signUpDTO.getEmail())
                .map(exists -> {
                    if (exists)
                        throw new IllegalArgumentException("Email already exists");
                    User user = new User();
                    user.setEmail(signUpDTO.getEmail());
                    user.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));
                    return userRepository.save(user);
                }).thenReturn(ApiResponse.success());
    }

    public Mono<ApiResponse<String>> signIn(LoginDTO loginDTO) {
        return userRepository.findByEmail(loginDTO.getEmail())
                .map(user -> {
                    if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword()))
                        throw new IllegalArgumentException("Passwords do not match");
                    return ApiResponse.success(jwtService.generateAccessToken(user));
                });
    }
}
