package com.wom.ttchat.chatroom.adapter.in.web;


import com.wom.ttchat.chatroom.adapter.in.web.reqeust.ChatRequest;
import com.wom.ttchat.chatroom.application.port.in.Command.BanChatRoomCommand;
import com.wom.ttchat.chatroom.application.port.in.Command.EnterChatRoomCommand;
import com.wom.ttchat.chatroom.application.port.in.Command.QuitChatRoomCommand;
import com.wom.ttchat.chatroom.application.port.in.CreateChatRoomUseCase;
import com.wom.ttchat.chatroom.application.port.in.EnterChatRoomUseCase;
import com.wom.ttchat.chatroom.application.port.in.LoadChatRoomUseCase;
import com.wom.ttchat.chatroom.application.port.in.QuitChatRoomUseCase;
import com.wom.ttchat.chatroom.application.service.ChatRoomService;

import com.wom.ttchat.chatroom.domain.ChatRoom;
import com.wom.ttchat.common.ApiResponse;
import com.wom.ttchat.common.ApiUtils;
import com.wom.ttchat.common.dto.PageRequest;
import com.wom.ttchat.member.domain.Member;
import com.wom.ttchat.member.domain.Member.MemberId;
import com.wom.ttchat.message.application.WSMessageService;
import com.wom.ttchat.participant.application.service.RegisterParticipantService;
import com.wom.ttchat.participant.domain.Participant;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chat")
public class ChatRoomController {

    private final RegisterParticipantService registerParticipantService;
    private final EnterChatRoomUseCase enterChatRoomUseCase;
    private final QuitChatRoomUseCase quitChatRoomUseCase;
    private final CreateChatRoomUseCase createChatRoomUseCase;
    private final SimpMessageSendingOperations simpMessageSendingOperations;
    private final ChatRoomService chatRoomService;
    private final WSMessageService wsMessageService;
    private final LoadChatRoomUseCase loadChatRoomUseCase;

    @PostMapping("/create/direct")
    ApiResponse<?> createDirectChat(@RequestBody ChatRequest req,
                                    @RequestHeader("memberId") UUID hostId) throws Exception{
        ChatRoom chatRoom = createChatRoomUseCase.transactionalCreateDirectRoom(req, hostId);

        return ApiUtils.successCreateWithDataResponse(chatRoom);
    }

    @PostMapping("/create/accompany")
    ApiResponse<?> createAccompanyChat(@RequestBody ChatRequest req,
                                    @RequestHeader("memberId") UUID hostId) throws Exception {
        if (req.getAccompanyPostId() == null) {
            throw new IllegalStateException("동행글 아이디가 필요합니다.");
        }
        ChatRoom chatRoom = createChatRoomUseCase.transactionalCreateAccompanyRoom(req, hostId);
        return ApiUtils.successCreateWithDataResponse(chatRoom);
    }


    @PostMapping("/room/enter")
    ApiResponse<?> enterChatRoom(@RequestBody ChatRequest req,
                                 @RequestHeader ("memberId") UUID memberId) throws Exception {
        Member.MemberId member = new Member.MemberId(memberId);
        Participant participant = enterChatRoomUseCase.transactionalEnterChatRoom((new EnterChatRoomCommand(member, req.getChatId())));
        return ApiUtils.successCreateWithEmptyResponse();
    }


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
            loadChatRoomUseCase.loadChatRoomList(new MemberId(memberId), PageRequest.of(pageNo, pageSize)));
    }

}

