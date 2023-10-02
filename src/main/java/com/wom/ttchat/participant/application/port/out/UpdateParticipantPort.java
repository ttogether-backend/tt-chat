package com.wom.ttchat.participant.application.port.out;

import com.wom.ttchat.participant.domain.Participant;

import java.time.LocalDateTime;
import java.util.UUID;

public interface UpdateParticipantPort {
    void updateParticipant(UUID MemberId);
    Participant updateParticipant(Participant participant);
    void updateParticipantStatus(Participant participant);
    void updateParticipantReadAt(Long roomId, UUID memberId, LocalDateTime readAt);
}
