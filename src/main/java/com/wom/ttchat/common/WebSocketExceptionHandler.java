package com.wom.ttchat.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;


@ControllerAdvice
public class WebSocketExceptionHandler {

    @ExceptionHandler(IllegalStateException.class)
    public void handleCustomException(WebSocketSession session, Throwable ex) throws IOException {
        session.sendMessage(new TextMessage(ex.getMessage()));
    }
}