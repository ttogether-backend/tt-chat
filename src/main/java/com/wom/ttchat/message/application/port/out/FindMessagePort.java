package com.wom.ttchat.message.application.port.out;

import com.wom.ttchat.message.domain.Message;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface FindMessagePort {

	List<Message> findUnReadMessage(UUID roomUid, LocalDateTime readAt);
	List<Message> findReadMessageList(UUID roomUid, LocalDateTime readAt);
	List<Message> findUnReadMessageList(UUID roomUid, LocalDateTime readAt);
	List<Message> findBanBeforeUnReadMessage(UUID roomUid, LocalDateTime readAt, LocalDateTime banAt);
	List<Message> findBanBeforeUnReadMessageList(UUID roomUid, LocalDateTime readAt, LocalDateTime banAt);
}
