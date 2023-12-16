package com.wom.ttchat.participant.application.port.in;

import com.wom.ttchat.chatroom.adapter.out.entity.ChatRoomJpaEntity;
import com.wom.ttchat.participant.domain.ParticipantStatus;
import com.wom.ttchat.participant.infrastructure.adapter.out.entity.ParticipantJpaEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public interface UpdateParticipantUseCase {
    void updateParticipant(ChatRoomJpaEntity chatRoom, UUID participant, ParticipantStatus status);
    void updateReadAt(UUID roomId, UUID participant, LocalDateTime readAt) throws Exception;
}
