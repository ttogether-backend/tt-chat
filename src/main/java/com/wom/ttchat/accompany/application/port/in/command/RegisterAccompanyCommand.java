package com.wom.ttchat.accompany.application.port.in.command;

import com.wom.ttchat.accompany.domain.AccompanyStatus;
import com.wom.ttchat.member.domain.Member.MemberId;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegisterAccompanyCommand {
	private Long accompanyId;
	private Long accompanyPostId;
	private AccompanyStatus accompanyStatus;
	private String postTitle;
	private MemberId hostId;
}
