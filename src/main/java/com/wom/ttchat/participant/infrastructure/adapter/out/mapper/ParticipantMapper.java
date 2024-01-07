package com.wom.ttchat.participant.infrastructure.adapter.out.mapper;

import com.wom.ttchat.chatroom.adapter.out.entity.ChatRoomJpaEntity;
import com.wom.ttchat.chatroom.domain.ChatRoom;
import com.wom.ttchat.member.adapter.out.persistence.MemberJpaEntity;
import com.wom.ttchat.member.domain.Member;
import com.wom.ttchat.participant.domain.Participant;
import com.wom.ttchat.participant.domain.Participant.ParticipantId;
import com.wom.ttchat.participant.domain.ParticipantStatus;
import com.wom.ttchat.participant.infrastructure.adapter.in.request.ParticipantRequest;
import com.wom.ttchat.participant.infrastructure.adapter.out.entity.ParticipantJpaEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ParticipantMapper {
    public Participant mapToDomainEntity(ParticipantJpaEntity jpaEntity, Member member, ChatRoom chatRoom) {
        return Participant.of(
                new ParticipantId(jpaEntity.getUid()),
                member,
                chatRoom,
                ParticipantStatus.valueOf(jpaEntity.getStatus()),
                jpaEntity.getReadAt(),
                jpaEntity.getCreatedAt(),
                jpaEntity.getDeleteAt(),
                jpaEntity.getUpdatedAt(),
                jpaEntity.getSessionId()
        );
    }

    public ParticipantJpaEntity mapToJpaEntity(Participant participant, MemberJpaEntity memberJpaEntity,
                                               ChatRoomJpaEntity chatRoomJpaEntity) {
        return ParticipantJpaEntity.builder()
                .uid(participant.getParticipantId().getValue())
                .status(participant.getStatus().name())
                .member(memberJpaEntity)
                .readAt(participant.getReadAt())
                .deleteAt(participant.getDeletedAt())
                .room(chatRoomJpaEntity)
                .sessionId(participant.getSessionId())
                .build();
    }

    public ParticipantJpaEntity reqToJpaEntity(ParticipantRequest req) {
        return new ParticipantJpaEntity(
                null,
                null,
                req.getStatus() == null ? null : req.getStatus().name(),
                req.getMemberJpaEntity(),
                LocalDateTime.now(),
                null,
                req.getChatRoomJpaEntity(),
                req.getSessionId()
        );
    }
}