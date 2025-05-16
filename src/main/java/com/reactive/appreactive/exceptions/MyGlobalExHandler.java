package com.reactive.appreactive.exceptions;

import com.reactive.appreactive.dto.ApiResponse;
import com.reactive.appreactive.dto.ErrorData;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class MyGlobalExHandler {

    @ExceptionHandler
    public Mono<HttpEntity<ApiResponse<ErrorData>>> handle(IllegalArgumentException ex) {
        ApiResponse<ErrorData> response = ApiResponse.fail(ex.getMessage());
        return Mono.just(ResponseEntity.status(400).body(response));
    }
}
