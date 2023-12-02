package com.wom.ttchat.member.adapter.in.web.messaging.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateMemberEvent {
    private UUID memberId;
    private String nickname;
    private String accountStatus;
    private String certificationStatus;  // PRIMARY, SECONDARY
}
