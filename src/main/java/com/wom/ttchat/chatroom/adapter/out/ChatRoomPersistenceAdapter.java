package com.wom.ttchat.chatroom.adapter.out;

import com.wom.ttchat.chatroom.adapter.out.entity.ChatRoomJpaEntity;
import com.wom.ttchat.chatroom.adapter.out.mapper.ChatRoomMapper;
import com.wom.ttchat.chatroom.adapter.out.persistence.repository.ChatRoomJpaRepository;
import com.wom.ttchat.chatroom.application.port.out.FindChatRoomPort;
import com.wom.ttchat.chatroom.application.port.out.SaveChatRoomPort;
import com.wom.ttchat.chatroom.domain.ChatRoom;
import com.wom.ttchat.chatroom.domain.ChatRoomInfo;
import com.wom.ttchat.common.CommonCode;
import com.wom.ttchat.common.annotation.PersistenceAdapter;
import com.wom.ttchat.common.dto.PageRequest;
import com.wom.ttchat.member.adapter.out.persistence.MemberMapper;
import com.wom.ttchat.member.domain.Member.MemberId;
import jakarta.persistence.EntityNotFoundException;
import jakarta.ws.rs.NotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@PersistenceAdapter
public class ChatRoomPersistenceAdapter implements SaveChatRoomPort, FindChatRoomPort {

    private final ChatRoomJpaRepository chatRoomJpaRepository;
    private final ChatRoomMapper chatRoomMapper;
    private final MemberMapper memberMapper;
    @Override
    public void saveGroupChat(ChatRoomJpaEntity chatRoomJpaEntity) throws Exception {
        chatRoomJpaRepository.save(chatRoomJpaEntity);
    }

    @Override
    public ChatRoom saveGroupChat(ChatRoom chatRoom) {
        ChatRoomJpaEntity saved = chatRoomJpaRepository.save(
                chatRoomMapper.mapToJpaEntity(
                chatRoom,
                memberMapper.mapToJpaEntity(chatRoom.getHostMemberId()),
                        null
        ));
        return chatRoomMapper.mapToDomainEntity(
                saved,
                memberMapper.mapToDomainEntity(saved.getHostMemberId()),
                null
        );
    }

    @Override
    public ChatRoom saveDirectChat(ChatRoom chatRoom) {
        ChatRoomJpaEntity saved = chatRoomJpaRepository.save(
                chatRoomMapper.mapToJpaEntity(
                        chatRoom,
                        memberMapper.mapToJpaEntity(chatRoom.getHostMemberId()),
                        memberMapper.mapToJpaEntity(chatRoom.getPartMemberId())
                ));
        return chatRoomMapper.mapToDomainEntity(
                saved,
                memberMapper.mapToDomainEntity(saved.getHostMemberId()),
                memberMapper.mapToDomainEntity(saved.getPartMemberId())
        );
    }

    @Override
    public ChatRoomJpaEntity findByUId(UUID chatRoomUId) throws Exception {
        return chatRoomJpaRepository.findByUid(chatRoomUId)
                .orElseThrow(() ->
                        new NotFoundException(
                                CommonCode.NOT_FOUND_CHATROOM.getMessage()
                        )
                );
    }

    @Override
    public ChatRoom findByUid(UUID chatRoomUID) throws Exception{
        ChatRoomJpaEntity chatRoomJpaEntity = chatRoomJpaRepository.findByUid(chatRoomUID)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                CommonCode.NOT_FOUND_CHATROOM.getMessage()
                        )
                );
        return chatRoomMapper.mapToDomainEntity(
                chatRoomJpaEntity, memberMapper.mapToDomainEntity(chatRoomJpaEntity.getHostMemberId()),
                memberMapper.mapToDomainEntity(chatRoomJpaEntity.getPartMemberId()));
    }

    @Override
    public List<ChatRoomInfo> findChatRoomListByMemberId(MemberId memberId,
        PageRequest pageRequest) {
        return chatRoomJpaRepository.findChatRoomListByMemberId(memberId, pageRequest);
    }

    @Override
    public ChatRoom findByAccompanyPostId(Long accompanyPostId) {
        Optional<ChatRoomJpaEntity> chatRoomJpaEntity = chatRoomJpaRepository.findByAccompanyPostId(accompanyPostId);
        if (chatRoomJpaEntity.isPresent()) {
            return chatRoomMapper.mapToDomainEntity(
                    chatRoomJpaEntity.get(), memberMapper.mapToDomainEntity(chatRoomJpaEntity.get().getHostMemberId()),
                            memberMapper.mapToDomainEntity(chatRoomJpaEntity.get().getPartMemberId()));
        }
        return null;
    }
    @Override
    public UUID findUUIDByHostIdAndMemberId(UUID hostId, UUID guestId) {
        return chatRoomJpaRepository.findDirectRoomByHostAndGuestId(hostId, guestId);
    }


}
