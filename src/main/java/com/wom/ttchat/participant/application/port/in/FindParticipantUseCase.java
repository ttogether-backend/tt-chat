package com.wom.ttchat.participant.application.port.in;


import com.wom.ttchat.chatroom.adapter.out.entity.ChatRoomJpaEntity;
import com.wom.ttchat.participant.domain.Participant;
import com.wom.ttchat.participant.infrastructure.adapter.in.request.ParticipantRequest;
import com.wom.ttchat.participant.infrastructure.adapter.out.entity.ParticipantJpaEntity;

import java.util.List;
import java.util.UUID;

public interface FindParticipantUseCase {
    boolean isParticipant(UUID roomUId, UUID memberId) throws Exception;
    List<Participant> getAllParticipants(UUID roomId) throws  Exception;
}
