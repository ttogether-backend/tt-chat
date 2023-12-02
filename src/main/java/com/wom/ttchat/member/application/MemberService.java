package com.wom.ttchat.member.application;

import com.wom.ttchat.common.annotation.UseCase;
import com.wom.ttchat.member.adapter.in.web.messaging.event.CreateMemberEvent;
import com.wom.ttchat.member.application.port.in.UpdateMemberUserCase;
import com.wom.ttchat.member.application.port.in.command.MemberCommand;
import com.wom.ttchat.member.application.port.in.command.UpdateProfileImagePathCommand;
import com.wom.ttchat.member.application.port.out.LoadMemberPort;
import com.wom.ttchat.member.application.port.out.UpdateMemberPort;
import com.wom.ttchat.member.domain.AccountStatus;
import com.wom.ttchat.member.domain.CertificationStatus;
import com.wom.ttchat.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@UseCase
public class MemberService implements UpdateMemberUserCase {

	private final UpdateMemberPort updateMemberPort;
	private final LoadMemberPort loadMemberPort;

	@Transactional
	@Override
	public void registerMember(MemberCommand memberCommand) {
		Member member = Member.create(memberCommand.getMemberId(), memberCommand.getNickname(),
			memberCommand.getBio(), memberCommand.getCertificationStatus(), memberCommand.getAccountStatus());

		updateMemberPort.updateMember(member);
	}

	@Transactional
	@Override
	public void updateMember(MemberCommand memberCommand) {
		Member member = loadMemberPort.loadMember(memberCommand.getMemberId());
		member.updateMemberInfo(memberCommand.getNickname(), memberCommand.getBio(),
			memberCommand.getCertificationStatus(), memberCommand.getAccountStatus());

		updateMemberPort.updateMember(member);

	}

	@Transactional
	@Override
	public void updateProfileImagePath(UpdateProfileImagePathCommand updateProfileImagePathCommand) {
		Member member = loadMemberPort.loadMember(updateProfileImagePathCommand.getMemberId());
		member.updateProfileImagePath(updateProfileImagePathCommand.getProfileImagePath());

		updateMemberPort.updateMember(member);
	}

	@Transactional
	public void createMember(CreateMemberEvent createMemberEvent) {
		Member member = Member.create(
				new Member.MemberId(createMemberEvent.getMemberId()),
				createMemberEvent.getNickname(),
				null,
				CertificationStatus.valueOf(createMemberEvent.getCertificationStatus()),
				AccountStatus.valueOf(createMemberEvent.getAccountStatus())
		);

		updateMemberPort.updateMember(member);
	}
}
