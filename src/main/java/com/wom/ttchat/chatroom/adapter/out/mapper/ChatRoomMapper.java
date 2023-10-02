package com.wom.ttchat.chatroom.adapter.out.mapper;

import com.wom.ttchat.chatroom.adapter.out.entity.ChatRoomJpaEntity;
import com.wom.ttchat.chatroom.domain.ChatRoom;
import com.wom.ttchat.member.adapter.out.persistence.MemberJpaEntity;
import com.wom.ttchat.member.domain.Member;
import com.wom.ttchat.participant.infrastructure.adapter.out.entity.ParticipantJpaEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChatRoomMapper {
    public ChatRoom mapToDomainEntity(ChatRoomJpaEntity jpaEntity, Member member) {
        return ChatRoom.of(
                jpaEntity.getUid(),
                jpaEntity.getId(),
                jpaEntity.getName(),
                jpaEntity.getAccompanyPostId(),
                member,
                jpaEntity.isGroup(),
                jpaEntity.getCreatedAt()
        );
    }

    public ChatRoomJpaEntity mapToJpaEntity(ChatRoom chatRoom, MemberJpaEntity memberJpaEntity) {
        return new ChatRoomJpaEntity(
                chatRoom.getChatRoomId(),
                chatRoom.getChatRoomUUID(),
                chatRoom.getName(),
                chatRoom.getAccompanyPostId(),
                memberJpaEntity,
                chatRoom.isGroup(),
                chatRoom.getCreatedAt()
        );
    }
}
