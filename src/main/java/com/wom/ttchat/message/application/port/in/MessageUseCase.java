package com.wom.ttchat.message.application.port.in;

import com.wom.ttchat.member.domain.Member.MemberId;
import com.wom.ttchat.message.adapter.out.persistence.MessageJpaEntity;
import com.wom.ttchat.message.domain.Message;
import java.util.List;
import java.util.UUID;

public interface MessageUseCase {
    MessageJpaEntity saveMessage(MessageJpaEntity message);
    List<Message> getAllMessagesInChatRoom(UUID roomId, MemberId memberId) throws Exception;
}
