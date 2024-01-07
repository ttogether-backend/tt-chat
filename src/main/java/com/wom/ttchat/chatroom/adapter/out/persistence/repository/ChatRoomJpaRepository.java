package com.wom.ttchat.chatroom.adapter.out.persistence.repository;

import com.wom.ttchat.chatroom.adapter.out.entity.ChatRoomJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ChatRoomJpaRepository extends JpaRepository<ChatRoomJpaEntity, Long>, ChatRoomRepositoryCustom {

    Optional<ChatRoomJpaEntity> findByUid(UUID uid);
    Optional<ChatRoomJpaEntity> findByAccompanyPostId(Long accompanyPostId);

}
