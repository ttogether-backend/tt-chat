package com.wom.ttchat.message.adapter.out.persistence;

import com.wom.ttchat.message.adapter.in.request.MessageRequest;
import com.wom.ttchat.message.domain.Message;
import com.wom.ttchat.message.domain.Message.MessageId;
import com.wom.ttchat.message.domain.MessageType;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MessageMapper {

	public Message mapToDomainEntity(MessageJpaEntity messageJpaEntity) {
		return Message.generatorMessage(
			new MessageId(messageJpaEntity.getId()),
			messageJpaEntity.getRoomUId(),
			messageJpaEntity.getMemberId(),
			messageJpaEntity.getContent(),
			messageJpaEntity.getType(),
			messageJpaEntity.getCreateAt().minusHours(9)
		);
	}

	public MessageJpaEntity mpaToJpaEntity(Message message) {
		return new MessageJpaEntity(
			message.getMessageId() == null ? null : message.getMessageId().getValue(),
			message.getRoomUID(),
			message.getMemberId(),
			message.getContent(),
			message.getMessageType(),
			message.getCreateAt()
		);
	}

	public MessageJpaEntity mapRequestToJpaEntity(MessageRequest message) {
		return new MessageJpaEntity(
				null,
				message.getRoomId(),
				message.getSenderId(),
				message.getContent(),
				message.getMessageType(),
				LocalDateTime.now().plusHours(9)
		);
	}
}
