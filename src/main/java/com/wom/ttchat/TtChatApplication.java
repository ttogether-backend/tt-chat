package com.wom.ttchat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@EnableJpaAuditing
@EnableDiscoveryClient
@SpringBootApplication
@EnableWebSocket
public class TtChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(TtChatApplication.class, args);
    }

}