package com.wom.ttchat.chatroom.application.port.out;

import com.wom.ttchat.chatroom.adapter.out.entity.ChatRoomJpaEntity;
import com.wom.ttchat.chatroom.domain.ChatRoom;

public interface SaveChatRoomPort {

    void saveChatRoom(ChatRoomJpaEntity chatRoomJpaEntity) throws Exception;
    ChatRoom saveChatRoom(ChatRoom chatRoom);

}
