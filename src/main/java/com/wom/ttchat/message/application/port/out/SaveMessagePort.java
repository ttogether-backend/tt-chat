package com.wom.ttchat.message.application.port.out;

import com.wom.ttchat.message.adapter.out.persistence.MessageJpaRepository;
import com.wom.ttchat.message.domain.Message;

public interface SaveMessagePort {

    void updateMessage(Message message) throws Exception;

}
