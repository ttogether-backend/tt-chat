package com.wom.ttchat.chatroom.adapter.in.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wom.ttchat.chatroom.adapter.in.messaging.event.ExitAccompanyEvent;
import com.wom.ttchat.chatroom.adapter.in.messaging.event.KickAccompanyEvent;
import com.wom.ttchat.chatroom.adapter.out.messaging.ChatRollBackProducer;
import com.wom.ttchat.chatroom.application.service.ChatRoomService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChatEventConsumer {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ChatRoomService chatRoomService;
    private final ChatRollBackProducer rollBackProducer;

    @KafkaListener(topics = "${kafka.topic.sub.together.exit-accompany}", errorHandler = "kafkaErrorHandler")
    @Transactional
    public void listenExitAccompany(String message) throws JsonProcessingException {
        try {
            ExitAccompanyEvent exitAccompanyEvent = objectMapper.readValue(message, ExitAccompanyEvent.class);
            log.info("[Kafka-Event] topic : exit accompany, received message : {}", message);
            chatRoomService.transactionalExitChatRoom(exitAccompanyEvent);
            log.info("[Kafka-Event] topic : exit accompany, consumed successfully");
        } catch (Exception e) {
            log.error("[Kafka-Event] topic : exit accompany, received message : {}, error message : {}", message, e.getMessage());
            // needs roll back event
//            rollBackProducer.rollBackExitChat(exitAccompanyEvent);
        }
    }

    @KafkaListener(topics = "${kafka.topic.sub.together.kick-accompany}")
    public void listenKickAccompany(String message) throws JsonProcessingException {
        try {
            KickAccompanyEvent kickAccompanyEvent = objectMapper.readValue(message, KickAccompanyEvent.class);
            log.info("[Kafka-Event] topic : kick accompany, received message : {}", message);
            chatRoomService.transactionalBanUser(kickAccompanyEvent);
            log.info("[Kafka-Event] topic : kick accompany, consumed successfully");
        } catch (Exception e) {
            log.error("[Kafka-Event] topic : kick accompany, received message : {}, error message : {}", message, e.getMessage());
            // needs roll back event
        }
    }
}
