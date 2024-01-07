package com.wom.ttchat.message.application.port.out;

import com.wom.ttchat.message.adapter.out.persistence.MessageJpaEntity;

public interface MessagePostPort {
    MessageJpaEntity saveMessage(MessageJpaEntity messageJpaEntity) throws Exception;
}
