package com.wom.ttchat.member.application.port.in.command;

import com.wom.ttchat.member.domain.Member.MemberId;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateProfileImagePathCommand {
	private final MemberId memberId;
	private final String profileImagePath;
}
