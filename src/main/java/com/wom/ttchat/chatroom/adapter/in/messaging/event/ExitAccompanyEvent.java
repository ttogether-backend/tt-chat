package com.wom.ttchat.chatroom.adapter.in.messaging.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExitAccompanyEvent {
    private UUID memberId;
    private Long accompanyId;
    private Long accompanyMemberId;
}
