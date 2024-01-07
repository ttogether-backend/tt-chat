package com.wom.ttchat.chatroom.application.port.in;

import com.wom.ttchat.chatroom.adapter.in.web.reqeust.ChatRequest;
import com.wom.ttchat.chatroom.application.port.in.Command.CreateChatRoomCommand;
import com.wom.ttchat.chatroom.application.port.in.Command.CreateDirectChatCommand;
import com.wom.ttchat.chatroom.domain.ChatRoom;
import jakarta.transaction.Transactional;

import java.util.UUID;

public interface CreateChatRoomUseCase {
    ChatRoom createGroupChat(CreateChatRoomCommand command) throws Exception;
    ChatRoom createDirectChat(CreateDirectChatCommand command) throws Exception;
    ChatRoom transactionalCreateAccompanyRoom(ChatRequest req, UUID hostId) throws Exception;
    @Transactional
    ChatRoom transactionalCreateDirectRoom(UUID guestId, UUID hostId) throws Exception;
}
