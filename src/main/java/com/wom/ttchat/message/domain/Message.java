package com.wom.ttchat.message.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.*;
import org.bson.types.ObjectId;

@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Message {
	private MessageId messageId;
	private String roomUID;
	private String memberId;
	private String nickname;
	private String content;
	private MessageType messageType;
	private LocalDateTime createAt;


	@Value
	public static class MessageId {
		String value;
	}

	public static Message generatorMessage(
            MessageId messageId,
            String roomUId,
			String memberId,
			String nickname,
            String content,
            MessageType messageType,
            LocalDateTime createAt) {
		return new Message(messageId, roomUId, memberId, nickname, content, messageType, createAt);
	}

	// 객체 생성, 초기화
	public static Message create(
			String roomUId,
			String memberId,
			String nickname,
			String content,
			MessageType messageType,
			LocalDateTime createAt
	){
		return new Message(null, roomUId, memberId, nickname, content, messageType, createAt);
	}

	//  UUID와 정보를 사용하여 Product 객체 생성 및 초기화
	public static Message of(
			MessageId messageId,
			String roomUId,
			String memberId,
			String nickname,
			String content,
			MessageType messageType,
			LocalDateTime createAt
	){
		return new Message(messageId, roomUId, memberId, nickname, content, messageType, createAt);
	}
}
