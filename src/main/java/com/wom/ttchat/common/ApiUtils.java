package com.wom.ttchat.common;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
public final class ApiUtils {

    public static ApiResponse<Void> successWithEmptyResponse() {
        return ApiResponse.<Void>builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .build();
    }

    public static <T> ApiResponse<T> createSuccessWithDataResponse(T t) {
        return ApiResponse.<T>builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .data(t)
                .build();
    }

    public static ApiResponse<Void> successCreateWithEmptyResponse() {
        return ApiResponse.<Void>builder()
                .code(HttpStatus.CREATED.value())
                .success(true)
                .build();
    }

    public static <T> ApiResponse<T> successCreateWithDataResponse(T t) {
        return ApiResponse.<T>builder()
                .code(HttpStatus.CREATED.value())
                .success(true)
                .data(t)
                .build();
    }

}