package com.wom.ttchat.chatroom.adapter.out.entity;

import com.wom.ttchat.member.adapter.out.persistence.MemberJpaEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "chatroom")
@SequenceGenerator(
        name = "chat_seq_gen",
        sequenceName = "chat_room_id_seq",
        allocationSize=1
)
@Builder
@DynamicInsert
public class ChatRoomJpaEntity {
    @Id
    @GeneratedValue(
            strategy=GenerationType.SEQUENCE,
            generator="chat_seq_gen"
    )
    private Long id;
    @Column
    private UUID uid;
    @Column
    private String name;
    @Column
    private Long accompanyPostId;
    @ManyToOne
    @JoinColumn(name = "host_member_id")
    private MemberJpaEntity hostMemberId;
    @ManyToOne
    @JoinColumn(name = "part_member_id")
    private MemberJpaEntity partMemberId;
    @Column
    private boolean isGroup;
    @Column
    private LocalDateTime createdAt;

//    @ManyToMany
//    @JoinTable(
//            name = "chatroom_participant",
//            joinColumns = @JoinColumn(name = "room_id"),
//            inverseJoinColumns = @JoinColumn(name = "participant_id")
//    )
//    private List<ParticipantJpaEntity> participants = new ArrayList();
//
//    public void addParticipants(ParticipantJpaEntity participant) {
//        participants.add(participant);
//        participant.getChatRooms().add(this);
//    }

//    public List<ParticipantJpaEntity> getParticipants() { return participants; }
}
