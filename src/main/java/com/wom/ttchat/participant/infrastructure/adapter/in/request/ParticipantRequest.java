package com.wom.ttchat.participant.infrastructure.adapter.in.request;
import com.wom.ttchat.chatroom.adapter.out.entity.ChatRoomJpaEntity;
import com.wom.ttchat.member.adapter.out.persistence.MemberJpaEntity;
import com.wom.ttchat.participant.domain.ParticipantStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParticipantRequest {
    private String memberId;
    private MemberJpaEntity memberJpaEntity;
    private String roomId;
    private ChatRoomJpaEntity chatRoomJpaEntity;
    private ParticipantStatus status;
    private String sessionId;
}
