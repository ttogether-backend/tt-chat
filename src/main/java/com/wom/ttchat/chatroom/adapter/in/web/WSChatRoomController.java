package com.wom.ttchat.chatroom.adapter.in.web;

import com.wom.ttchat.chatroom.application.port.in.LoadChatRoomUseCase;
import com.wom.ttchat.chatroom.domain.ChatRoomInfo;
import com.wom.ttchat.common.ApiResponse;
import com.wom.ttchat.common.ApiUtils;
import com.wom.ttchat.common.dto.PageRequest;
import com.wom.ttchat.member.domain.Member.MemberId;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
//@WebAdapter
@Tag(name = "채팅", description = "채팅방 목록 조회")
@Controller
@RequiredArgsConstructor
public class WSChatRoomController {

	private final LoadChatRoomUseCase loadChatRoomUseCase;

	private final SimpMessageSendingOperations simpMessageSendingOperations;

	@MessageMapping("/chat-room")
	public void getChatRoomList(SimpMessageHeaderAccessor headerAccessor) throws Exception {
		String memberId = headerAccessor.getFirstNativeHeader("memberId");
		List<ChatRoomInfo> chatRoomList = loadChatRoomUseCase.loadChatRoomList(new MemberId(UUID.fromString(memberId)), PageRequest.of(null, null));
		simpMessageSendingOperations.convertAndSend("/chat-room/", chatRoomList);
	}
}
