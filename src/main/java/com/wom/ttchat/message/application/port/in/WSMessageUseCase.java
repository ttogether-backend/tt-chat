package com.wom.ttchat.message.application.port.in;

import com.wom.ttchat.member.domain.Member.MemberId;
import com.wom.ttchat.message.adapter.in.request.MessageRequest;
import com.wom.ttchat.message.adapter.out.persistence.MessageJpaEntity;
import com.wom.ttchat.message.domain.Message;
import java.util.List;
import java.util.UUID;

public interface WSMessageUseCase {
    Message saveMessage(MessageRequest message) throws Exception;
    List<Message> getMessageList(UUID roomId, MemberId memberId) throws Exception;
}
