package com.reactive.appreactive.controller;

import com.reactive.appreactive.dto.UserDTO;
import com.reactive.appreactive.mapper.UserMapper;
import com.reactive.appreactive.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @GetMapping
    public Mono<List<UserDTO>> listOfUsers() {
        return userRepository.findAll()
                .map(userMapper::toDTO)
                .collectList();
    }

}
