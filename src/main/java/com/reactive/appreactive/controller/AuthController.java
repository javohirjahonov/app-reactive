package com.reactive.appreactive.controller;

import com.reactive.appreactive.dto.ApiResponse;
import com.reactive.appreactive.dto.LoginDTO;
import com.reactive.appreactive.dto.SignUpDTO;
import com.reactive.appreactive.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public Mono<ApiResponse<Boolean>> register(@Valid @RequestBody SignUpDTO signUpDTO) {
        return authService.signUp(signUpDTO);
    }


    @PostMapping("/login")
    public Mono<ApiResponse<String>> login(@Valid @RequestBody LoginDTO loginDTO) {
        return authService.signIn(loginDTO);
    }
}
