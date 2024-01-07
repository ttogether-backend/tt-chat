package com.wom.ttchat.chatroom.adapter.in.web.reqeust;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.wom.ttchat.member.domain.Member;
import lombok.Getter;

import java.util.UUID;

@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
@Getter
public class ChatRequest {
    private UUID chatId;
    private UUID memberId;
    private Long accompanyPostId;
    private String name;
}
