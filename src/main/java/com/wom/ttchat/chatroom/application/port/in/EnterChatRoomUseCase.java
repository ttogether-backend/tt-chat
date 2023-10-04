package com.wom.ttchat.chatroom.application.port.in;

import com.wom.ttchat.chatroom.application.port.in.Command.EnterChatRoomCommand;
import com.wom.ttchat.participant.domain.Participant;

public interface EnterChatRoomUseCase {
    Participant enterChatRoom(EnterChatRoomCommand command) throws Exception;
    Participant transactionalEnterChatRoom(EnterChatRoomCommand command) throws Exception;

    boolean isAccompanyMember(EnterChatRoomCommand command) throws Exception;
}
