//package com.wom.ttchat.chatroom.application.service;
//import com.wom.ttchat.chatroom.adapter.out.entity.ChatRoomJpaEntity;
//import com.wom.ttchat.chatroom.application.port.in.Command.CreateChatRoomCommand;
//import com.wom.ttchat.chatroom.application.port.in.CreateChatCommand;
//import com.wom.ttchat.member.adapter.out.persistence.MemberJpaEntity;
//import com.wom.ttchat.chatroom.application.port.in.CreateChatRoomUseCase;
//import com.wom.ttchat.chatroom.adapter.out.persistence.repository.ChatRoomJpaRepository;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.Optional;
//import java.util.UUID;
//
//@Service
//@RequiredArgsConstructor
//public class CreateChatRoomService implements CreateChatRoomUseCase {
//    private final ChatRoomJpaRepository chatJpaRepository;
//
//    @Override
//    public ChatRoomJpaEntity createRoom(CreateChatRoomCommand command) {
//        return chatJpaRepository.save(
//                ChatRoomJpaEntity.builder()
//                        .name(command.getName())
//                        .accompanyPostId(command.getAccompanyPostId())
//                        .isGroup(isGroup)
//                        .hostMemberId(MemberJpaEntity.builder().id(hostId).build())
//                        .createdAt(LocalDateTime.now())
//                        .build()
//        );
//    }
//
//    public Optional<ChatRoomJpaEntity> findById(Long id) {
//        return chatJpaRepository.findById(id);
//    }
//
////    @Override
////    public ChatRoomJpaEntity createRoom(CreateChatCommand command) {
////        ChatRoomJpaEntity chatRoom = chatRepository.save(ChatRoomJpaEntity.create(command.getHostId(), command.getMemberId()));
////        //member register service 함수로 대체
//
////        participantsRepository.save(Participants.create(chatRoom.getId() , command.getHostId()));
////        participantsRepository.save(Participants.create(chatRoom.getId(), command.getMemberId()));
////        return chatRepository.save(chatRoom);
////    }
//}
