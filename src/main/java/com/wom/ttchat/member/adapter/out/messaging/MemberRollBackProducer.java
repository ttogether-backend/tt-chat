package com.wom.ttchat.member.adapter.out.messaging;

import com.wom.ttchat.chatroom.adapter.in.messaging.event.JoinAccompanyEvent;
import com.wom.ttchat.common.Utils.MessageUtils;
import com.wom.ttchat.member.adapter.in.web.messaging.event.CreateMemberEvent;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class MemberRollBackProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.topic.pub.rollback.create-member}")
    private String createTopic;

    @Transactional
    public void rollBackCreateMember(CreateMemberEvent createMemberEvent) {
        try {
            log.info("[Kafka-Event] topic : rollback-create-member, message : {}", createMemberEvent.toString());
            kafkaTemplate.send(MessageUtils.makeMessage(createTopic, createMemberEvent));
        } catch (Exception e) {
            log.error("[Kafka-Event] topic : rollback-create-member, error : {}", e.getMessage());
        }
    }

}
