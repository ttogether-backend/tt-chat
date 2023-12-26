package com.wom.ttchat.chatroom.application.port.in;

import com.wom.ttchat.chatroom.adapter.in.web.reqeust.ChatRequest;
import com.wom.ttchat.chatroom.application.port.in.Command.CreateChatRoomCommand;
import com.wom.ttchat.chatroom.domain.ChatRoom;

import java.util.UUID;

public interface CreateChatRoomUseCase {
    ChatRoom createGroupChat(CreateChatRoomCommand command) throws Exception;

    ChatRoom transactionalCreateDirectRoom(ChatRequest req, UUID hostId) throws Exception;
    ChatRoom transactionalCreateAccompanyRoom(ChatRequest req, UUID hostId) throws Exception;

}
