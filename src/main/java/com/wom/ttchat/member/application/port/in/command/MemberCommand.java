package com.wom.ttchat.member.application.port.in.command;

import com.wom.ttchat.member.domain.AccountStatus;
import com.wom.ttchat.member.domain.CertificationStatus;
import com.wom.ttchat.member.domain.Member.MemberId;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberCommand {
	private final MemberId memberId;
	private final String nickname;
	private final String bio;
	private final CertificationStatus certificationStatus;
	private final AccountStatus accountStatus;

}
