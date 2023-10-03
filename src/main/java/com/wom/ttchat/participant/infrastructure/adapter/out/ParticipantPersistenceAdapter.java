package com.wom.ttchat.participant.infrastructure.adapter.out;

import com.wom.ttchat.chatroom.adapter.out.mapper.ChatRoomMapper;
import com.wom.ttchat.chatroom.domain.ChatRoom;
import com.wom.ttchat.common.CommonCode;
import com.wom.ttchat.common.annotation.PersistenceAdapter;
import com.wom.ttchat.member.adapter.out.persistence.MemberMapper;
import com.wom.ttchat.member.domain.Member.MemberId;
import com.wom.ttchat.participant.application.port.out.FindParticipantPort;
import com.wom.ttchat.participant.application.port.out.UpdateParticipantPort;
import com.wom.ttchat.participant.domain.Participant;
import com.wom.ttchat.participant.domain.ParticipantStatus;
import com.wom.ttchat.participant.infrastructure.adapter.out.entity.ParticipantJpaEntity;
import com.wom.ttchat.participant.infrastructure.adapter.out.mapper.ParticipantMapper;
import com.wom.ttchat.participant.infrastructure.adapter.out.repository.ParticipantRepository;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@PersistenceAdapter
@RequiredArgsConstructor
public class ParticipantPersistenceAdapter implements UpdateParticipantPort, FindParticipantPort {

    private final ParticipantRepository participantRepository;
    private final ParticipantMapper participantMapper;
    private final MemberMapper memberMapper;
    private final ChatRoomMapper chatRoomMapper;

    @Override
    public void updateParticipant(UUID MemberId) {

    }

    @Override
    public Participant updateParticipant(Participant participant) {
        ParticipantJpaEntity updated = participantRepository.save(
                participantMapper.mapToJpaEntity(participant,
                        memberMapper.mpaToJpaEntity(participant.getMember()),
                        chatRoomMapper.mapToJpaEntity(participant.getChatRoom(), null))
        );
        return participantMapper.mapToDomainEntity(
                updated,
                participant.getMember(),
                participant.getChatRoom()
        );
    }

    @Override
    public void updateParticipantStatus(Participant participant) {
        participantRepository.updateStatusByRoomIdAndMemberId(
                participant.getStatus().name(),
                participant.getChatRoom().getChatRoomId(),
                participant.getMember().getId().getValue()
        );
    }

    @Override
    public void updateParticipantStatusAndDeleteAt(Participant participant) {
        participantRepository.updateDeleteAtAndStatusByRoomIdAndMemberId(participant.getDeletedAt(), participant.getStatus().name(),
                participant.getChatRoom().getChatRoomId(), participant.getMember().getId().getValue());
    }

    @Override
    public void updateParticipantReadAt(Long roomId, UUID memberId, LocalDateTime readAt) {
        participantRepository.updateReadyAtByRoomIdAndMemberId(readAt, roomId, memberId);
    }

    @Override
    public List<Participant> findJoinParticipantListByRoomId(ChatRoom chatRoom) {
        List<ParticipantJpaEntity> participantJpaEntityList = participantRepository.findAllByRoomIdAndStatusEquals(
            chatRoom.getChatRoomId(), ParticipantStatus.JOINED.name());

        List<Participant> participantList = new ArrayList<>();
        for(ParticipantJpaEntity jpaEntity : participantJpaEntityList) {
            participantList.add(
                participantMapper.mapToDomainEntity(jpaEntity,
                    memberMapper.mapToDomainEntity(jpaEntity.getMember()),
                    chatRoom)
            );
        }

        return participantList;

    }

    @Override
    public Participant findParticipantByRoomIdAndMemberId(ChatRoom chatRoom, MemberId memberId) {
        ParticipantJpaEntity jpaEntity =
            participantRepository.findByRoomIdAndMemberId(chatRoom.getChatRoomId(), memberId.getValue());


        if (jpaEntity == null) {
            return null;
        }
        return participantMapper.mapToDomainEntity(
            jpaEntity,
            memberMapper.mapToDomainEntity(jpaEntity.getMember()),
            chatRoom
        );
    }

    @Override
    public Participant findParticipantByRoomIdAndMemberIdAndStatus(ChatRoom chatRoom, MemberId memberId, String status) {
        ParticipantJpaEntity jpaEntity =
                participantRepository.findByRoomIdAndMemberIdAndStatus(chatRoom.getChatRoomId(), memberId.getValue(), status);

        if (jpaEntity == null) {
            return null;
        }
        return participantMapper.mapToDomainEntity(
                jpaEntity,
                memberMapper.mapToDomainEntity(jpaEntity.getMember()),
                chatRoom
        );
    }

    @Override
    public Participant findJoinParticipant(ChatRoom chatRoom, MemberId memberId) {
        ParticipantJpaEntity jpaEntity
            = participantRepository.findByRoomIdAndMemberIdAndStatusEquals(
                chatRoom.getChatRoomId(), memberId.getValue(), ParticipantStatus.JOINED.name()
        ).orElseThrow( () -> new EntityNotFoundException(CommonCode.UNAUTHORIZED_ACCESS_CHATROOM.getMessage()));

        return participantMapper.mapToDomainEntity(
            jpaEntity,
            memberMapper.mapToDomainEntity(jpaEntity.getMember()),
            chatRoom
        );
    }
}
