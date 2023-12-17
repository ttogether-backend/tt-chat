package com.wom.ttchat.message.application.port.out;

import com.wom.ttchat.message.domain.Message;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface FindMessagePort {

	List<Message> findUnReadMessage(UUID roomUid, LocalDateTime readAt);
	List<Message> findReadMessages(UUID roomUid, LocalDateTime readAt);
	List<Message> findUnReadMessages(UUID roomUid, LocalDateTime readAt);
	List<Message> findBanBeforeUnReadMessage(UUID roomUid, LocalDateTime readAt, LocalDateTime banAt);
	List<Message> findBanBeforeUnReadMessageList(UUID roomUid, LocalDateTime readAt, LocalDateTime banAt);
	long findUnReadMessagesCount(UUID roomUid, LocalDateTime readAt);
	Message findLastSentMessage(UUID roomUid);

}
