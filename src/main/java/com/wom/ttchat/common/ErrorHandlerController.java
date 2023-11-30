package com.wom.ttchat.common;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.MessagingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.wom.ttchat.common.ApiResponse.ApiError;

@RestControllerAdvice
public class ErrorHandlerController {

  @ExceptionHandler({IllegalStateException.class, IllegalArgumentException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ApiResponse<String> handleBadRequestException(Exception e) {
    return ApiResponse.<String>builder()
        .code(HttpStatus.BAD_REQUEST.value())
        .success(false)
        .error(new ApiError(HttpStatus.BAD_REQUEST.name(), e.getMessage()))
        .build();
  }

//  @ExceptionHandler(EntityNotFoundException.class)
//  @ResponseStatus(HttpStatus.NOT_FOUND)
//  public ApiResponse<String> handleNotFoundException(Exception e) {
//    return ApiResponse.<String>builder()
//        .code(HttpStatus.NOT_FOUND.value())
//        .success(false)
//        .error(new ApiError(HttpStatus.NOT_FOUND.name(), e.getMessage()))
//        .build();
//  }

  @ExceptionHandler
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ApiResponse<String> handleInternalServerErrorException(Exception e) {
    return ApiResponse.<String>builder()
        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .success(false)
        .error(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.name(), e.getMessage()))
        .build();
  }

//  @ExceptionHandler(MessagingException.class)
//  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//  public ApiResponse<String> handleMessagingException(Exception e) {
//    return ApiResponse.<String>builder()
//            .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
//            .success(false)
//            .error(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.name(), e.getMessage()))
//            .build();
//  }



}
