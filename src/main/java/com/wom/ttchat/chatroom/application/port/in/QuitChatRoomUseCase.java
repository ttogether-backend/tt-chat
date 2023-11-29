package com.wom.ttchat.chatroom.application.port.in;

import com.wom.ttchat.chatroom.adapter.in.messaging.AccompanyEvent;
import com.wom.ttchat.chatroom.application.port.in.Command.BanChatRoomCommand;
import com.wom.ttchat.chatroom.application.port.in.Command.QuitChatRoomCommand;
import com.wom.ttchat.participant.domain.Participant;
import jakarta.transaction.Transactional;

public interface QuitChatRoomUseCase {
    Participant quitChatRoom(QuitChatRoomCommand command) throws Exception;
    @Transactional
    Participant transactionalQuiChatRoom(AccompanyEvent event) throws Exception;

    Participant banUserInChatRoom(BanChatRoomCommand command) throws Exception;
    void transactionalBanUser(BanChatRoomCommand command) throws Exception;
    void banAccept(BanChatRoomCommand command) throws Exception;
}
