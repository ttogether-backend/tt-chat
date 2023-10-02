package com.wom.ttchat.common.configuration;

import com.wom.ttchat.common.handler.StompHandler;
import com.wom.ttchat.common.handler.WebSockHandler;
import com.wom.ttchat.common.interceptor.WebSocketHandshakeInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;


@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class WebSocketConfig extends WebSocketConfigurer implements WebSocketMessageBrokerConfigurer {

    private final StompHandler stompHandler;
    private final WebSockHandler webSockChatHandler;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/sub");
        registry.setApplicationDestinationPrefixes("/pub");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry
                .addEndpoint("/api/v1/chat/ws")
                .setAllowedOrigins("*")
                .addInterceptors(new WebSocketHandshakeInterceptor());
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(stompHandler);
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSockChatHandler, "/api/v1/chat/greet").setAllowedOrigins("*");
    }
}