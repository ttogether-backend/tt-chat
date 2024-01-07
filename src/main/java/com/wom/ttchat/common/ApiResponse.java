package com.wom.ttchat.common;


import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Getter
@Builder
public class ApiResponse<T> {

    private int code;
    private boolean success;
    private T data;
    private ApiError error;

    @Value
    public static class ApiError {

        private String code;
        private String message;
    }
}
