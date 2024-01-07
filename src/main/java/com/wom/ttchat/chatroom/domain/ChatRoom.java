package com.wom.ttchat.chatroom.domain;

import com.wom.ttchat.chatroom.adapter.out.entity.ChatRoomJpaEntity;
import com.wom.ttchat.member.domain.Member;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ChatRoom {

    private UUID chatRoomUUID;
    private Long chatRoomId;
    private String name;
    private Long accompanyPostId;
    private Member hostMemberId;
    private Member partMemberId;
    private boolean isGroup;
    private LocalDateTime createdAt;

    public static ChatRoom of(
            UUID chatRoomUUID,
            Long chatRoomId,
            String name,
            Long accompanyPostId,
            Member hostMemberId,
            Member partMemberId,
            boolean isGroup,
            LocalDateTime createdAt
    ) {
        return new ChatRoom(
                chatRoomUUID,
                chatRoomId,
                name,
                accompanyPostId,
                hostMemberId,
                partMemberId,
                isGroup,
                createdAt
        );
    }
}
