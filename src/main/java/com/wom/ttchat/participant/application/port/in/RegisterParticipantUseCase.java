package com.wom.ttchat.participant.application.port.in;


import com.wom.ttchat.chatroom.adapter.out.entity.ChatRoomJpaEntity;
import com.wom.ttchat.participant.infrastructure.adapter.in.request.ParticipantRequest;
import com.wom.ttchat.participant.infrastructure.adapter.out.entity.ParticipantJpaEntity;

import java.util.UUID;

public interface RegisterParticipantUseCase {
    ParticipantJpaEntity registerParticipant(ChatRoomJpaEntity chatRoom, UUID participant);

    ParticipantJpaEntity saveParticipant(ParticipantRequest req);
}
