package com.wom.ttchat.member.adapter.in.web.request;

import java.util.UUID;
import lombok.Getter;

@Getter
public class RegisterMemberRequest {
	UUID memberId;
	String nickname;
	String bio;
	String certificationStatus;
	String accountStatus;
}
