package com.wom.ttchat.participant.domain;

import com.wom.ttchat.chatroom.domain.ChatRoom;
import com.wom.ttchat.member.domain.Member;
import java.util.UUID;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Participant {

    private ParticipantId participantId;
    private Member member;
    private ChatRoom chatRoom;
    private ParticipantStatus status;
    private LocalDateTime readAt;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
    private LocalDateTime updatedAt;
    private String sessionId;

    @Value
    public static class ParticipantId {
        UUID value;
    }

    public static Participant of(
            ParticipantId participantId,
            Member member,
            ChatRoom chatRoom,
            ParticipantStatus status,
            LocalDateTime readAt,
            LocalDateTime createdAt,
            LocalDateTime deletedAt,
            LocalDateTime updatedAt,
            String sessionId
    ) {

        return new Participant(
                participantId,
                member,
                chatRoom,
                status,
                readAt,
                createdAt,
                deletedAt,
                updatedAt,
                sessionId
        );
    }

    public static Participant createParticipant(
            Member member,
            ChatRoom chatRoom,
            ParticipantStatus status,
            LocalDateTime readAt,
            LocalDateTime createdAt,
            LocalDateTime deletedAt,
            LocalDateTime updatedAt,
            String sessionId
    ) {

        return new Participant(
                null,
                member,
                chatRoom,
                status,
                readAt,
                createdAt,
                deletedAt,
                updatedAt,
                sessionId
        );
    }

    public void changeStatusToJoined() {
        this.status = ParticipantStatus.JOINED;
    }

    public void changeStatusToLeft() {
        this.status = ParticipantStatus.LEFT;
    }

    public void changeStatusToBanned() {
        this.status = ParticipantStatus.BANNED;
    }

    public boolean isJoined() {
        return this.status == ParticipantStatus.JOINED;
    }

    public boolean isLeft() {
        return this.status == ParticipantStatus.LEFT;
    }

    public boolean isBanned() {
        return this.status == ParticipantStatus.BANNED;
    }

    public void updateReadAt(LocalDateTime readAt) {
        // 메시지를 읽은 시간 업데이트 로직을 추가
        this.readAt = readAt;
    }
}
