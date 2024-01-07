package com.wom.ttchat.chatroom.adapter.in.web.response;

import com.wom.ttchat.participant.infrastructure.adapter.in.request.ParticipantRequest;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class ChatRoomResponse {

    UUID chatRoomId;
    String name;
    boolean isGroup;
    Long accompanyPostId;
    UUID hostMemberId;

    List<ParticipantRequest> participants;
}
