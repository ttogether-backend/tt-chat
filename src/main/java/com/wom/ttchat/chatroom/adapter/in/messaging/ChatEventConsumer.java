package com.wom.ttchat.chatroom.adapter.in.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wom.ttchat.accompany.domain.Accompany;
import com.wom.ttchat.chatroom.application.port.in.Command.QuitChatRoomCommand;
import com.wom.ttchat.chatroom.application.service.ChatRoomService;
import com.wom.ttchat.member.domain.Member;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChatEventConsumer {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ChatRoomService chatRoomService;

    @KafkaListener(topics = "${kafka.topic.sub.together.exit-accompany}")
    @Transactional
    public void listenExitAccompany(String message) throws JsonProcessingException {
        try {
            AccompanyEvent accompanyEvent = objectMapper.readValue(message, AccompanyEvent.class);
            log.info("[Kafka-Event] topic : exit accompany, received message : {}", message);
            chatRoomService.transactionalQuiChatRoom(accompanyEvent);
            log.info("[Kafka-Event] topic : exit accompany, consumed successfully");
        } catch (Exception e) {
            log.error("[Kafka-Event] topic : exit accompany, received message : {}, error message : {}", message, e.getMessage());
            // needs roll back event
        }
    }
}
