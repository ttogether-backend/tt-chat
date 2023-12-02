package com.wom.ttchat.message.adapter.in.web;

import com.wom.ttchat.common.ApiResponse;
import com.wom.ttchat.common.ApiUtils;
import com.wom.ttchat.member.domain.Member.MemberId;
import com.wom.ttchat.message.adapter.in.request.MessageRequest;
import com.wom.ttchat.message.adapter.out.persistence.MessageJpaEntity;
import com.wom.ttchat.message.application.MessageService;
import com.wom.ttchat.message.application.port.in.MessageUseCase;
import com.wom.ttchat.message.domain.MessageType;
import java.util.UUID;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
//@WebAdapter

@RestController
@RequiredArgsConstructor
@Tag(name = "메세지", description = "채팅방 내 메세지 조회")
@RequestMapping("/api/v1/chat")
public class MessageController {
    @Autowired
    private final MessageService messageService;
	private final MessageUseCase messageUseCase;


    // 아래에서 사용되는 convertAndSend 를 사용하기 위해서 서언
    // convertAndSend 는 객체를 인자로 넘겨주면 자동으로 Message 객체로 변환 후 도착지로 전송한다.
    private final SimpMessageSendingOperations simpMessageSendingOperations;

    /*
        /sub/channel/12345      - 구독(channelId:12345)
        /pub/hello              - 메시지 발행
    */

    // @SendToUser 라는 어노테이션이 존재하는데 1:N으로 통신하는 @SendTo 와 다르게 @SendToUser는 1:1로 통신한다고 합니다.
    // 보통 sendTo 는 구독 경로가 /topic 으로 시작하며, sendToUser는 /queue로 시작하는데 보편적이라고 합니다.

//    @MessageMapping("/hello")
//    public void message(MessageRequest message) {
//        try {
//            // TODO: channel -> roomId
//            simpMessageSendingOperations.convertAndSend("/sub/room/" + message.getRoomUid(), message);
//            System.out.println("/sub/room/" + message.getRoomUid());
//            messageService.saveMessage(MessageJpaEntity.builder()
//                    .roomUId(message.getRoomUId())
//                    .type(MessageType.valueOf("MSG"))
//                    .content(message.getContent())
////                    .memberId(message.getSender())
//                    .createAt(LocalDateTime.now())
//                    .build());
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//    }

	@GetMapping("/{room_id}/message")
	ApiResponse<?> getMessageList(@RequestHeader("memberId") UUID memberId, @PathVariable UUID room_id) throws Exception{
			return ApiUtils.successCreateWithDataResponse(
					messageUseCase.getMessageList(room_id, new MemberId(memberId)));
	}

}
