package com.wom.ttchat.chatroom.adapter.in.messaging.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OpenAccompanyEvent{
    private UUID memberId;         // 주최자 memberId
    private Long accompanyId;      // 동행 id
    private String startAt;     // 동행 시작일
    private String endAt;       // 동행 종료일
    private String accompanyPostName;    // 동행글 제목
}