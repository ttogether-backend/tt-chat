package com.wom.ttchat.chatroom.adapter.out.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wom.ttchat.chatroom.adapter.in.messaging.event.ExitAccompanyEvent;
import com.wom.ttchat.common.Utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ChatRollBackProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    @Value("${kafka.topic.pub.rollback.exit-accompany}")
    private String exitTopic;

    public void rollBackExitChat(String message) {
        try {
            kafkaTemplate.send(MessageUtils.makeMessage(exitTopic, message));
        } catch (Exception e) {
            log.error("[Kafka-Event] topic : rollback-exit-accompany, error : {}", e.getMessage());

        }
    }
}
