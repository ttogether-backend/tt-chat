package com.wom.ttchat.message.adapter.out.persistence;

import com.wom.ttchat.message.domain.MessageType;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageJpaRepository  extends MongoRepository<MessageJpaEntity, String> {
	List<MessageJpaEntity> findByRoomUIdAndCreateAtAfterAndTypeInOrderByCreateAtDesc(
		String roomUid, LocalDateTime readAt, List<MessageType> typeList);
	List<MessageJpaEntity> findByRoomUIdAndCreateAtAfterOrderByCreateAt(String roomUid, LocalDateTime readAt);
	List<MessageJpaEntity> findByRoomUIdAndCreateAtBetweenAndTypeInOrderByCreateAtDesc(
		String roomUid, LocalDateTime readAt, LocalDateTime banAt, List<MessageType> typeList);
	List<MessageJpaEntity> findByRoomUIdAndCreateAtBeforeOrderByCreateAt(String roomUid, LocalDateTime readAt);
	List<MessageJpaEntity> findByAndRoomUIdAndCreateAtBetweenOrderByCreateAt(
		String roomUid, LocalDateTime readAt, LocalDateTime banAt);
	MessageJpaEntity findTopByRoomUIdOrderByCreateAtDesc(String roomUid);
	long countByRoomUIdAndCreateAtAfter(String roomUid, LocalDateTime readAt);
}
