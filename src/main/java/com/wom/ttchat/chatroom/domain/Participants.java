package com.wom.ttchat.chatroom.domain;//package com.wom.ttchat.chatroom.domain;
//
//import lombok.*;
//import org.bson.types.ObjectId;
//
//import java.time.LocalDateTime;
//import java.util.UUID;
//
//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//public class Participants {
//
//    private ParticipantId participantId;
//    private UUID memberId;
//    private ChatRoom chatRoom;
//    private ParticipantStatus status;
//    private LocalDateTime readAt;
//    private LocalDateTime createdAt;
//    private LocalDateTime deletedAt;
//
//    @Value
//    public static class ParticipantId {
//        ObjectId value;
//    }
//
//    public static Participants generatorParticipants(
//            ParticipantId participantId,
//            UUID memberId,
//            ChatRoom chatRoom,
//            ParticipantStatus status,
//            LocalDateTime readAt,
//            LocalDateTime createdAt,
//            LocalDateTime deletedAt
//    ) {
//
//        return new Participants(
//                participantId,
//                memberId,
//                chatRoom,
//                status,
//                readAt,
//                createdAt,
//                deletedAt
//        );
//    }
//
//    public static Participants createParticipants(
//            UUID memberId,
//            ChatRoom chatRoom,
//            ParticipantStatus status,
//            LocalDateTime readAt,
//            LocalDateTime createdAt,
//            LocalDateTime deletedAt
//    ) {
//
//        return new Participants(
//                null,
//                memberId,
//                chatRoom,
//                status,
//                readAt,
//                createdAt,
//                deletedAt
//        );
//    }
//
//    public enum ParticipantStatus {
//        JOINED, LEFT, KICKED
//    }
//
//    public void changeStatusToJoined() {
//        this.status = ParticipantStatus.JOINED;
//    }
//
//    public void changeStatusToLeft() {
//        this.status = ParticipantStatus.LEFT;
//    }
//
//    public void changeStatusToKicked() {
//        this.status = ParticipantStatus.KICKED;
//    }
//
//    public boolean isJoined() {
//        return this.status == ParticipantStatus.JOINED;
//    }
//
//    public boolean isLeft() {
//        return this.status == ParticipantStatus.LEFT;
//    }
//
//    public boolean isKicked() {
//        return this.status == ParticipantStatus.KICKED;
//    }
//
//    public void updateReadAt(LocalDateTime readAt) {
//        // 메시지를 읽은 시간 업데이트 로직을 추가
//        this.readAt = readAt;
//    }
//}
