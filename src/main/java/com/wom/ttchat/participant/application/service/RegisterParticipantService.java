package com.wom.ttchat.participant.application.service;

import com.wom.ttchat.chatroom.adapter.out.entity.ChatRoomJpaEntity;
import com.wom.ttchat.common.annotation.UseCase;
import com.wom.ttchat.member.adapter.out.persistence.MemberJpaEntity;
import com.wom.ttchat.participant.application.port.in.RegisterParticipantUseCase;
import com.wom.ttchat.participant.infrastructure.adapter.in.request.ParticipantRequest;
import com.wom.ttchat.participant.infrastructure.adapter.out.entity.ParticipantJpaEntity;
import com.wom.ttchat.participant.infrastructure.adapter.out.mapper.ParticipantMapper;
import com.wom.ttchat.participant.infrastructure.adapter.out.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Transactional
@UseCase
public class RegisterParticipantService implements RegisterParticipantUseCase {
    private final ParticipantRepository participantRepository;
    private final ParticipantMapper participantMapper;


    @Override
    public ParticipantJpaEntity registerParticipant(ChatRoomJpaEntity chatRoomJpaEntity, UUID participant) {
        ParticipantJpaEntity resp = participantRepository.save(
                ParticipantJpaEntity.builder()
                        .member(MemberJpaEntity.builder().id(participant).build())
                        .deleteAt(null)
                        .readAt(null)
                        .room(chatRoomJpaEntity)
                        .status("0")
                        .build()
        );
        return resp;

    }

    @Override
    public ParticipantJpaEntity saveParticipant(ParticipantRequest req) {
        req.setMemberJpaEntity(
                MemberJpaEntity.builder()
                        .id(UUID.fromString(req.getMemberId()))
                        .build()
        );

//        req.setChatRoomJpaEntity(
//                ChatRoomJpaEntity.builder()
//                        .uid(UUID.fromString(req.getRoomId()))
//                        .build()
//        );

        return participantRepository.save(participantMapper.reqToJpaEntity(req));
    }
}
