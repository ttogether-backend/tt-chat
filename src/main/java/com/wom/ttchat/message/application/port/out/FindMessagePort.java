package com.wom.ttchat.message.application.port.out;

import com.wom.ttchat.message.domain.Message;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface FindMessagePort {

	List<Message> findUnReadMessage(UUID roomUid, LocalDateTime readAt);
	List<Message> findMessageList(UUID roomUid, LocalDateTime readAt);
}
