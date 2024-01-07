package com.wom.ttchat.common;

import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/chat")
public class HealthCheckController {

    private final Environment environment;

    @GetMapping("/health_check")
    public String status(){
        return String.format("It's working in chat service on PORT %s",environment.getProperty("server.port"));
    }
}