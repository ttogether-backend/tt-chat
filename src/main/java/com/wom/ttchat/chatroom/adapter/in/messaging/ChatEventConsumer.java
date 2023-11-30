package com.wom.ttchat.chatroom.adapter.in.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wom.ttchat.chatroom.adapter.in.messaging.event.ExitAccompanyEvent;
import com.wom.ttchat.chatroom.adapter.in.messaging.event.KickAccompanyEvent;
import com.wom.ttchat.chatroom.adapter.out.messaging.ChatRollBackProducer;
import com.wom.ttchat.chatroom.application.service.ChatRoomService;
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

    @KafkaListener(topics = "${kafka.topic.sub.together.exit-accompany}", errorHandler = "kafkaErrorHandler",
            clientIdPrefix = "exit-accompany-client")
    public void listenExitAccompany(String message) throws JsonProcessingException {
        ExitAccompanyEvent exitAccompanyEvent = new ExitAccompanyEvent();
        try {
            exitAccompanyEvent = objectMapper.readValue(message, ExitAccompanyEvent.class);
            log.info("[Kafka-Event] topic : exit accompany, received message : {}", message);
            chatRoomService.transactionalExitChatRoom(exitAccompanyEvent);
            log.info("[Kafka-Event] topic : exit accompany, consumed successfully");
        } catch (Exception e) {
            log.error("[Kafka-Event] topic : exit accompany, received message : {}, error message : {}", message, e.getMessage());
            rollBackProducer.rollBackExitAccompany(exitAccompanyEvent);
        }
    }

    @KafkaListener(topics = "${kafka.topic.sub.together.kick-accompany}", errorHandler = "kafkaErrorHandler",
            clientIdPrefix = "kick-accompany-client")
    public void listenKickAccompany(String message) throws JsonProcessingException {
        KickAccompanyEvent kickAccompanyEvent = new KickAccompanyEvent();
        try {
            kickAccompanyEvent = objectMapper.readValue(message, KickAccompanyEvent.class);
            log.info("[Kafka-Event] topic : kick accompany, received message : {}", message);
            chatRoomService.transactionalBanUser(kickAccompanyEvent);
            log.info("[Kafka-Event] topic : kick accompany, consumed successfully");
        } catch (Exception e) {
            log.error("[Kafka-Event] topic : kick accompany, received message : {}, error message : {}", message, e.getMessage());
            rollBackProducer.rollBackKickAccompany(kickAccompanyEvent);
        }
    }
}
