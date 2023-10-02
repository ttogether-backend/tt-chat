package com.wom.ttchat.chatroom.application.port.in;

import com.wom.ttchat.chatroom.adapter.out.entity.ChatRoomJpaEntity;
import com.wom.ttchat.chatroom.domain.ChatRoom;

import java.util.UUID;

public interface CreateChatUseCase {
    ChatRoomJpaEntity createRoom(CreateChatCommand command, UUID hostId, boolean isGroup);
}
