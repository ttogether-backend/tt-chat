package com.wom.ttchat.common.Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.ProducerRecord;

public class MessageUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static ProducerRecord<String, String> makeMessage(String topic, Object sendObject) throws JsonProcessingException {
        String message = objectMapper.writeValueAsString(sendObject);
        return new ProducerRecord<>(topic, message);
    }
}