package com.reactive.appreactive.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private String message;

    private boolean success;

    private List<ErrorData> errors;

    private T data;

    //success
    private ApiResponse(String message) {
        this.message = message;
        this.success = true;
    }

    private ApiResponse(T data) {
        this.data = data;
        this.success = true;
    }

    private ApiResponse() {
        this.success = true;
    }

    private ApiResponse(List<ErrorData> errors) {
        this.errors = errors;
    }

    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>();
    }

    public static <T> ApiResponse<T> success(String message) {
        return new ApiResponse<>(message);
    }

    public static <E> ApiResponse<E> success(E data) {
        return new ApiResponse<>(data);
    }

    public static <E> ApiResponse<E> fail(String msg) {
        return new ApiResponse<>(List.of(ErrorData.error(msg)));
    }

    public static <E> ApiResponse<E> fail(List<ErrorData> errors) {
        return new ApiResponse<>(errors);
    }

}
