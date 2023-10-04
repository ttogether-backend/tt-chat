package com.wom.ttchat.message.adapter.out.persistence;

import com.wom.ttchat.message.domain.MessageType;
import java.time.LocalDateTime;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

public interface MessageJpaRepository  extends MongoRepository<MessageJpaEntity, String> {
	List<MessageJpaEntity> findAllByRoomUIdAndCreateAtAfterAndTypeInOrderByCreateAtDesc(
		String roomUid, LocalDateTime readAt, List<MessageType> typeList);
	List<MessageJpaEntity> findAllByRoomUIdAndCreateAtAfterOrderByCreateAt(String roomUid, LocalDateTime readAt);
	List<MessageJpaEntity> findAllByRoomUIdAndCreateAtBetweenAndTypeInOrderByCreateAtDesc(
		String roomUid, LocalDateTime readAt, LocalDateTime banAt, List<MessageType> typeList);

	List<MessageJpaEntity> findAllByAndRoomUIdAndCreateAtBetweenOrderByCreateAt(
		String roomUid, LocalDateTime readAt, LocalDateTime banAt);

}
