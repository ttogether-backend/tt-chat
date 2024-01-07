package com.wom.ttchat.chatroom.adapter.in.web;


import com.wom.ttchat.chatroom.adapter.in.web.reqeust.ChatRequest;
import com.wom.ttchat.chatroom.application.port.in.CreateChatRoomUseCase;
import com.wom.ttchat.chatroom.application.port.in.EnterChatRoomUseCase;
import com.wom.ttchat.chatroom.application.port.in.LoadChatRoomUseCase;
import com.wom.ttchat.chatroom.application.port.in.QuitChatRoomUseCase;
import com.wom.ttchat.chatroom.application.service.ChatRoomService;

import com.wom.ttchat.chatroom.domain.ChatRoom;
import com.wom.ttchat.common.ApiResponse;
import com.wom.ttchat.common.ApiUtils;
import com.wom.ttchat.common.dto.PageRequest;
import com.wom.ttchat.member.domain.Member.MemberId;
import com.wom.ttchat.message.application.WSMessageService;
import com.wom.ttchat.message.application.port.in.MessageUseCase;
import com.wom.ttchat.message.domain.Message;
import com.wom.ttchat.participant.application.service.RegisterParticipantService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@Tag(name = "채팅", description = "1:1채팅방 생성, 채팅방 목록 조회")
@RequiredArgsConstructor
@RequestMapping("/api/v1/chat")
public class ChatRoomController {
    private final CreateChatRoomUseCase createChatRoomUseCase;
    private final LoadChatRoomUseCase loadChatRoomUseCase;
    private final MessageUseCase messageUseCase;

    @GetMapping("/chat-room/direct/{guestId}")
    ApiResponse<?> findDirectChat(@RequestHeader("memberId") UUID hostId,
                                  @PathVariable UUID guestId) throws Exception{
//        ChatRoom chatRoom = createChatRoomUseCase.transactionalCreateDirectRoom(req, hostId);
        UUID chatId = loadChatRoomUseCase.loadChatRoomByHostAndGuestId(hostId, guestId);
        log.info("find chatroom : {}", chatId);
        if (chatId == null) {
            ChatRoom savedChat = createChatRoomUseCase.transactionalCreateDirectRoom(guestId, hostId);
            chatId = savedChat.getChatRoomUUID();
            log.info("new chat room is created, id = {}", savedChat.getChatRoomUUID());
        }
        return ApiUtils.successCreateWithDataResponse(chatId);
    }
//
//    @PostMapping("/room/enter")
//    ApiResponse<?> enterChatRoom(@RequestBody ChatRequest req,
//                                 @RequestHeader ("memberId") UUID memberId) throws Exception {
//        Member.MemberId member = new Member.MemberId(memberId);
//        Participant participant = enterChatRoomUseCase.transactionalEnterChatRoom((new EnterChatRoomCommand(member, req.getChatId())));
//        return ApiUtils.successCreateWithEmptyResponse();
//    }


//    @PostMapping("/room/quit")
//    ApiResponse<?> quitChatRoom(@RequestBody ChatRequest req,
//                                @RequestHeader ("memberId") UUID memberId) throws Exception {
//        Member.MemberId member = new Member.MemberId(memberId);
//        Participant participant = quitChatRoomUseCase.transactionalQuiChatRoom((new QuitChatRoomCommand(member, req.getChatId())));
//        return ApiUtils.successCreateWithEmptyResponse();
//    }


//    @PostMapping("/room/ban")
//    ApiResponse<?> banUserInChatRoom(@RequestBody ChatRequest req,
//                               @RequestHeader("memberId") UUID memberId) throws Exception {
//        Member.MemberId host = new MemberId(memberId);
//        Member.MemberId member = new MemberId(req.getMemberId());
//
//        quitChatRoomUseCase.transactionalBanUser((new BanChatRoomCommand(host, member, req.getChatId())));
//        return ApiUtils.successCreateWithEmptyResponse();
//    }

//    @PostMapping("/room/ban/accept")
//    ApiResponse<?> banAccept(@RequestBody ChatRequest req,
//                               @RequestHeader("memberId") UUID memberId) throws Exception {
//        Member.MemberId member = new MemberId(memberId);
//
//        quitChatRoomUseCase.banAccept((new BanChatRoomCommand(null, member, req.getChatId())));
//        return ApiUtils.successCreateWithEmptyResponse();
//    }



    @GetMapping("/chat-room")
    ApiResponse<?> findChatRoomList(@RequestHeader("memberId") UUID memberId,
        @RequestParam(required = false) Integer pageNo, @RequestParam(required = false) Integer pageSize)throws Exception {

        return ApiUtils.successCreateWithDataResponse(
                loadChatRoomUseCase.loadChatRoomList(new MemberId(UUID.fromString(memberId.toString())), PageRequest.of(null, null)));

    }

    @GetMapping("/chat-room/{roomId}")
    public List<Message> getMessageList(@RequestHeader("memberId") UUID memberId,
                                        @PathVariable UUID roomId) throws Exception {
        List<Message> messageList = messageUseCase.getAllMessagesInChatRoom(roomId, new MemberId(memberId));
        return messageList;
    }

}

