package com.wom.ttchat.chatroom.adapter.in.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wom.ttchat.chatroom.adapter.in.messaging.event.ExitAccompanyEvent;
import com.wom.ttchat.chatroom.adapter.in.messaging.event.JoinAccompanyEvent;
import com.wom.ttchat.chatroom.adapter.in.messaging.event.KickAccompanyEvent;
import com.wom.ttchat.chatroom.adapter.in.messaging.event.OpenAccompanyEvent;
import com.wom.ttchat.chatroom.adapter.out.messaging.ChatRollBackProducer;
import com.wom.ttchat.chatroom.application.port.in.Command.CreateChatRoomCommand;
import com.wom.ttchat.chatroom.application.service.ChatRoomService;
import com.wom.ttchat.member.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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


    @KafkaListener(topics = {"${kafka.topic.sub.together.open-accompany}"}, errorHandler = "kafkaErrorHandler")
    public void listenOpenAccompany(@Payload String message) throws JsonProcessingException {
        OpenAccompanyEvent openAccompanyEvent = objectMapper.readValue(message, OpenAccompanyEvent.class);

        try{
            log.info("[Kafka-Event] topic : open accompany, received message : {}", message);
            UUID hostId = openAccompanyEvent.getMemberId();
            Long accompanyId = openAccompanyEvent.getAccompanyId();
            // 방 생성
            chatRoomService.createRoom(new CreateChatRoomCommand(
                    new Member.MemberId(hostId),
                    openAccompanyEvent.getAccompanyPostName(),
                    true,
                    accompanyId
            ));

            // 주최자의 채팅방 참여
            JoinAccompanyEvent accompanyEvent = new JoinAccompanyEvent(hostId, accompanyId, null);
            chatRoomService.joinChatRoom(accompanyEvent);

            log.info("[Kafka-Event] topic : open accompany, consumed successfully");
            throw new Exception("error");
        }catch (Exception e){
            log.error("[Kafka-Event] topic : open accompany, received message : {}, error message : {}", message, e.getMessage());
            rollBackProducer.rollBackOpenAccompany(openAccompanyEvent);
        }
    }

    @KafkaListener(topics = "${kafka.topic.sub.together.join-accompany}", errorHandler = "kafkaErrorHandler")
//    @SendTo("${kafka.topic.pub.rollback.join-accompany}")
    public void listenJoinAccompany(String message)  throws JsonProcessingException {
        JoinAccompanyEvent joinAccompanyEvent = objectMapper.readValue(message, JoinAccompanyEvent.class);

        try{
            log.info("[Kafka-Event] topic : join accompany, received message : {}", message);
            chatRoomService.joinChatRoom(joinAccompanyEvent);
            log.info("[Kafka-Event] topic : join accompany, consumed successfully");

            // throw new Exception("error");
        }catch (Exception e){
            log.error("[Kafka-Event] topic : join accompany, received message : {}, error message : {}", message, e.getMessage());
            rollBackProducer.rollBackJoinAccompany(joinAccompanyEvent);
        }
    }


}
