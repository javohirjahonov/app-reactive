package com.reactive.appreactive.repository;

import com.reactive.appreactive.entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserRepository extends ReactiveCrudRepository<User, UUID> {

    Mono<Boolean> existsByEmail(String email);

    Mono<User> findByEmail(String email);
}
