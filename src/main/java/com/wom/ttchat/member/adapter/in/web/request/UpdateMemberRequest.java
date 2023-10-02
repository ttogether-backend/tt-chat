package com.wom.ttchat.member.adapter.in.web.request;

import lombok.Getter;

@Getter
public class UpdateMemberRequest {
	String nickname;
	String bio;
	String certificationStatus;
	String accountStatus;
}
