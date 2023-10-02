package com.wom.ttchat.participant.infrastructure.adapter.out.entity;

import com.wom.ttchat.chatroom.adapter.out.entity.ChatRoomJpaEntity;
import com.wom.ttchat.common.BaseTimeEntity;
import com.wom.ttchat.member.adapter.out.persistence.MemberJpaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/*
* 채팅방 참여자
* */
@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "participants")
@SequenceGenerator(
        name = "part_seq_gen",
        sequenceName = "participants_id_seq",
        allocationSize=1
)
@DynamicInsert
public class ParticipantJpaEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(
            strategy=GenerationType.SEQUENCE,
            generator="part_seq_gen"
    )
    @Column(name = "id")
    private Long id;
    @Column
    @ColumnDefault("get_random_uuid()")
    private UUID uid;
    @Column
    private String status;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberJpaEntity member;

    @Column
    private LocalDateTime readAt;

    @Column
    private LocalDateTime deleteAt;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private ChatRoomJpaEntity room;

    @Column(name = "session_id")
    private String sessionId;



//    @ManyToMany
//    @JoinTable(
//            name = "participant_chatroom",
//            joinColumns = @JoinColumn(name = "participant_id"),
//            inverseJoinColumns = @JoinColumn(name = "room_id")
//    )
//    private List<ChatRoomJpaEntity> chatRooms = new ArrayList<>();
//    // 새로운 채팅방을 참여자에게 추가하는 메서드
//    public void addChatRoom(ChatRoomJpaEntity chatRoom) {
//        chatRooms.add(chatRoom);
//        chatRoom.getParticipants().add(this);
//    }
//
//    // 참여자가 속한 채팅방 목록을 가져오는 메서드
//    public List<ChatRoomJpaEntity> getChatRooms() {
//        return chatRooms;
//    }

}
