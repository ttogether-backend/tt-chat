package com.wom.ttchat.message.adapter.in.request;

 import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
 import com.wom.ttchat.message.domain.MessageType;
 import lombok.Builder;
 import lombok.Data;

 import java.util.UUID;

@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
@Builder
public class MessageRequest {
    private String roomId;
    private String senderId;
    private String nickname;
    private String content;
    private MessageType messageType;

    public void setSender(String senderId) {
        this.senderId = senderId;
    }

}