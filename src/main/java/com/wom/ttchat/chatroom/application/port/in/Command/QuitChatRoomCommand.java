package com.wom.ttchat.chatroom.application.port.in.Command;

import com.wom.ttchat.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class QuitChatRoomCommand {
    private Member.MemberId memberId;
    private Long accompanyId;
    private UUID roomId;
}
