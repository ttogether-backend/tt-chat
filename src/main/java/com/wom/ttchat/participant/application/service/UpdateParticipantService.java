package com.wom.ttchat.participant.application.service;

import com.wom.ttchat.chatroom.adapter.out.entity.ChatRoomJpaEntity;
import com.wom.ttchat.participant.application.port.in.UpdateParticipantUseCase;
import com.wom.ttchat.participant.domain.ParticipantStatus;
import com.wom.ttchat.participant.infrastructure.adapter.out.entity.ParticipantJpaEntity;
import com.wom.ttchat.participant.infrastructure.adapter.out.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Transactional
@Service
public class UpdateParticipantService implements UpdateParticipantUseCase {

    private final ParticipantRepository participantRepository;
    @Override
    public void updateParticipant(ChatRoomJpaEntity chatRoom, UUID participant, ParticipantStatus status) {
        participantRepository.updateStatusByRoomIdAndMemberId(status.name(), chatRoom.getId(), participant);
    }

}
