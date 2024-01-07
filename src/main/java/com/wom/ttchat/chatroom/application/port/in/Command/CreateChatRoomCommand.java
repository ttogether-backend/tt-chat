package com.wom.ttchat.chatroom.application.port.in.Command;

import com.wom.ttchat.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class CreateChatRoomCommand {
    private Member.MemberId hostId;
    private String name;
    private boolean isGroup;
    private Long accompany_post_id;
}
