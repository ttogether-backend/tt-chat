package com.wom.ttchat.message.adapter.in.web;

import com.wom.ttchat.chatroom.adapter.out.entity.ChatRoomJpaEntity;
import com.wom.ttchat.common.ApiResponse;
import com.wom.ttchat.common.ApiUtils;
import com.wom.ttchat.member.domain.Member.MemberId;
import com.wom.ttchat.message.adapter.in.request.MessageRequest;
import com.wom.ttchat.message.adapter.out.persistence.MessageJpaEntity;
import com.wom.ttchat.message.application.MessageService;
import com.wom.ttchat.message.application.port.in.MessageUseCase;
import com.wom.ttchat.message.application.port.in.WSMessageUseCase;
import com.wom.ttchat.message.domain.Message;
import com.wom.ttchat.message.domain.SystemMessage;
import com.wom.ttchat.participant.application.service.RegisterParticipantService;
import com.wom.ttchat.participant.application.service.UpdateParticipantService;
import com.wom.ttchat.participant.domain.ParticipantStatus;
import com.wom.ttchat.participant.infrastructure.adapter.out.entity.ParticipantJpaEntity;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
//@WebAdapter
@Controller
@RequiredArgsConstructor
public class WSMessageController {

    private final WSMessageUseCase messageUseCase;
    private final RegisterParticipantService registerParticipantService;
    private final UpdateParticipantService updateParticipantService;

    // 아래에서 사용되는 convertAndSend 를 사용하기 위해서 서언
    // convertAndSend 는 객체를 인자로 넘겨주면 자동으로 Message 객체로 변환 후 도착지로 전송한다.
    private final SimpMessageSendingOperations simpMessageSendingOperations;

    /*
        /sub/channel/12345      - 구독(channelId:12345)
        /pub/hello              - 메시지 발행
    */

    // @SendToUser 라는 어노테이션이 존재하는데 1:N으로 통신하는 @SendTo 와 다르게 @SendToUser는 1:1로 통신한다고 합니다.
    // 보통 sendTo 는 구독 경로가 /topic 으로 시작하며, sendToUser는 /queue로 시작하는데 보편적이라고 합니다.

    @MessageMapping("/message")
    @SendTo("/sub/room")
    public void message(@Payload MessageRequest message, SimpMessageHeaderAccessor headerAccessor) {
        try{

            String authorizationHeader = headerAccessor.getFirstNativeHeader("memberId");
            message.setSenderId(authorizationHeader);
            messageUseCase.saveMessage(message);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @MessageMapping("/{room_id}/message")
    public List<Message> getMessageList(@DestinationVariable UUID room_id, SimpMessageHeaderAccessor headerAccessor) throws Exception {

        String memberId = headerAccessor.getFirstNativeHeader("memberId");
        List<Message> messageList = messageUseCase.getMessageList(room_id, new MemberId(UUID.fromString(memberId)));
        simpMessageSendingOperations.convertAndSend("/sub/room/" + room_id, messageList);

        return messageList;
    }


//    @MessageMapping("/{roomId}/enter")
//    public void enter(@PathVariable String roomId, StompHeaderAccessor accessor) {
//        String memberId = accessor.getFirstNativeHeader("memberId");
//        log.info("user : {} " + SystemMessage.ENTER, memberId);
//        registerParticipantService.registerParticipant(ChatRoomJpaEntity
//                .builder().uid(UUID.fromString(roomId)).build(), UUID.fromString(memberId));
//        String msg = memberId + SystemMessage.ENTER;
//        simpMessageSendingOperations.convertAndSend("/sub/" + roomId, memberId);
//    }
//
//
//    @MessageMapping("/{roomId}/out")
//    public void exit(@PathVariable String roomId, StompHeaderAccessor accessor) {
//        String memberId = accessor.getFirstNativeHeader("memberId");
//        log.info("user : {} " + SystemMessage.EXIT, memberId);
//        updateParticipantService.updateParticipant(ChatRoomJpaEntity.builder()
//                .uid(UUID.fromString(roomId)).build(), UUID.fromString(memberId), ParticipantStatus.LEFT);
//        String msg = memberId + SystemMessage.ENTER;
//        simpMessageSendingOperations.convertAndSend("/sub/" + roomId, memberId);
//    }

    /* 연결 끊김 감지 */
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event){
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        log.info("disconnected : " + event.getCloseStatus());
    }

}
