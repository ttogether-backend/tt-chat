package com.wom.ttchat.participant.application.service;

import com.wom.ttchat.chatroom.adapter.out.entity.ChatRoomJpaEntity;
import com.wom.ttchat.chatroom.adapter.out.persistence.repository.ChatRoomJpaRepository;
import com.wom.ttchat.chatroom.application.port.out.FindChatRoomPort;
import com.wom.ttchat.chatroom.domain.ChatRoom;
import com.wom.ttchat.common.annotation.UseCase;
import com.wom.ttchat.member.adapter.out.persistence.MemberJpaEntity;
import com.wom.ttchat.participant.application.port.in.FindParticipantUseCase;
import com.wom.ttchat.participant.application.port.in.RegisterParticipantUseCase;
import com.wom.ttchat.participant.application.port.out.FindParticipantPort;
import com.wom.ttchat.participant.domain.Participant;
import com.wom.ttchat.participant.infrastructure.adapter.in.request.ParticipantRequest;
import com.wom.ttchat.participant.infrastructure.adapter.out.entity.ParticipantJpaEntity;
import com.wom.ttchat.participant.infrastructure.adapter.out.mapper.ParticipantMapper;
import com.wom.ttchat.participant.infrastructure.adapter.out.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Transactional
@UseCase
public class FindParticipantService implements FindParticipantUseCase {
    private final ParticipantRepository participantRepository;
    private final FindParticipantPort findParticipantPort;
    private final ChatRoomJpaRepository chatRoomJpaRepository;
    private final FindChatRoomPort findChatRoomPort;

    @Override
    public boolean isParticipant(UUID roomUId, UUID memberId) throws Exception {
        Optional<ChatRoomJpaEntity> room = chatRoomJpaRepository.findByUid(roomUId);
        if(room.isEmpty()){
            throw new IllegalArgumentException("존재하지 않거나 권한이 없는 채팅방입니다.");
        }

        return participantRepository.findByMemberIdAndRoomId(memberId,room.get().getId()).isPresent();
    }

    @Override
    public List<Participant> getAllParticipants(UUID roomId) throws Exception {
        ChatRoom room = findChatRoomPort.findByUid(roomId);
        List<Participant> participants = findParticipantPort.findJoinParticipantListByRoomId(room);
        return participants;
    }
}
