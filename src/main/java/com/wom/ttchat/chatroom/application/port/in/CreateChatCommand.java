package com.wom.ttchat.chatroom.application.port.in;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class CreateChatCommand {
//    private final UUID hostId;
    private final UUID memberId;
    private final Long accompanyPostId;
    private final String name;
}
