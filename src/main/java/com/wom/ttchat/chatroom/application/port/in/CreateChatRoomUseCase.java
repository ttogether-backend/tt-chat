package com.wom.ttchat.chatroom.application.port.in;

import com.wom.ttchat.chatroom.adapter.in.web.reqeust.ChatRequest;
import com.wom.ttchat.chatroom.application.port.in.Command.CreateChatRoomCommand;
import com.wom.ttchat.chatroom.domain.ChatRoom;

import java.util.UUID;

public interface CreateChatRoomUseCase {
    ChatRoom createRoom(CreateChatRoomCommand command) throws Exception;

    ChatRoom transactionalCreateRoom(ChatRequest req, UUID hostId) throws Exception;
}
