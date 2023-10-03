package com.wom.ttchat.chatroom.application.port.in;

import com.wom.ttchat.chatroom.application.port.in.Command.BanChatRoomCommand;
import com.wom.ttchat.chatroom.application.port.in.Command.QuitChatRoomCommand;
import com.wom.ttchat.participant.domain.Participant;

public interface QuitChatRoomUseCase {
    Participant quitChatRoom(QuitChatRoomCommand command) throws Exception;
    Participant transactionalQuiChatRoom(QuitChatRoomCommand command) throws Exception;
    Participant banChatRoom(BanChatRoomCommand command) throws Exception;
    void transactionalBanChatRoom(BanChatRoomCommand command) throws Exception;
}
