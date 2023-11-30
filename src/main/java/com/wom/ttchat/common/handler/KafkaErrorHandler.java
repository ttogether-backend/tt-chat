package com.wom.ttchat.common.handler;

import com.wom.ttchat.chatroom.adapter.out.messaging.ChatRollBackProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaErrorHandler implements KafkaListenerErrorHandler {
    private final ChatRollBackProducer chatRollBackProducer;

    @Override
    public Object handleError(Message<?> message, ListenerExecutionFailedException e) {
        return null;
    }

    @Override
    public Object handleError(Message<?> message, ListenerExecutionFailedException exception, Consumer<?, ?> consumer) {
        log.error("[KafkaErrorHandler] kafka message = [" + message.getPayload() +
                "], error message = [" + exception.getMessage() + "]");
//        ConsumerRecord<String, String> record = (ConsumerRecord<String, String>) message.getPayload();
        chatRollBackProducer.rollBackExitChat(message.getPayload().toString());
//        Map<TopicPartition, OffsetAndMetadata> offsets = new HashMap<>();
//        offsets.put(
//                new TopicPartition(record.topic(), record.partition()),
//                new OffsetAndMetadata(record.offset() + 1, null));
//        consumer.commitSync(offsets);
        return null;
//        return KafkaListenerErrorHandler.super.handleError(message, exception, consumer);
    }
}
