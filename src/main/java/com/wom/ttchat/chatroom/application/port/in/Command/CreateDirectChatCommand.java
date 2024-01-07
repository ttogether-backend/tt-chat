package com.wom.ttchat.chatroom.application.port.in.Command;

import com.wom.ttchat.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateDirectChatCommand {
    private Member.MemberId hostId;
    private Member.MemberId guestId;
}
