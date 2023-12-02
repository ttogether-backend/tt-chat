package com.wom.ttchat.member.adapter.in.web.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wom.ttchat.chatroom.adapter.in.messaging.event.ExitAccompanyEvent;
import com.wom.ttchat.member.adapter.in.web.messaging.event.CreateMemberEvent;
import com.wom.ttchat.member.adapter.out.messaging.MemberRollBackProducer;
import com.wom.ttchat.member.application.MemberService;
import com.wom.ttchat.member.application.port.in.command.MemberCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MemberEventConsumer {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final MemberService memberService;
    private final MemberRollBackProducer memberRollBackProducer;

    @KafkaListener(topics = "${kafka.topic.sub.auth.create-member}", errorHandler = "kafkaErrorHandler")
    public void listenExitAccompany(String message) throws JsonProcessingException {
        CreateMemberEvent createMemberEvent = new CreateMemberEvent();
        try {
            createMemberEvent = objectMapper.readValue(message, CreateMemberEvent.class);
            log.info("[Kafka-Event] topic : create-member, received message : {}", message);
            memberService.createMember(createMemberEvent);
            log.info("[Kafka-Event] topic : create-member, consumed successfully");
        } catch (Exception e) {
            log.error("[Kafka-Event] topic : create-member, received message : {}, error message : {}", message, e.getMessage());
            memberRollBackProducer.rollBackCreateMember(createMemberEvent);
        }
    }


}
