package com.wom.ttchat.chatroom.adapter.out.messaging;

import com.wom.ttchat.chatroom.adapter.in.messaging.event.ExitAccompanyEvent;
import com.wom.ttchat.chatroom.adapter.in.messaging.event.KickAccompanyEvent;
import com.wom.ttchat.common.Utils.MessageUtils;
import jakarta.transaction.Transactional;
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

    @Value("${kafka.topic.pub.rollback.kick-accompany}")
    private String kickTopic;

    @Transactional
    public void rollBackExitAccompany(ExitAccompanyEvent event) {
        try {
            log.info("[Kafka-Event] topic : rollback-exit-accompany, message : {}", event.toString());
            kafkaTemplate.send(MessageUtils.makeMessage(exitTopic, event));
        } catch (Exception e) {
            log.error("[Kafka-Event] topic : rollback-exit-accompany, error : {}", e.getMessage());
        }
    }

    @Transactional
    public void rollBackKickAccompany(KickAccompanyEvent event) {
        try {
            log.info("[Kafka-Event] topic : rollback-exit-accompany, message : {}", event.toString());
            kafkaTemplate.send(MessageUtils.makeMessage(kickTopic, event));
        } catch (Exception e) {
            log.error("[Kafka-Event] topic : rollback-exit-accompany, error : {}", e.getMessage());
        }
    }
}
