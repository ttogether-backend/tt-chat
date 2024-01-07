package com.wom.ttchat.member.application.port.in;

import com.wom.ttchat.member.application.port.in.command.MemberCommand;
import com.wom.ttchat.member.application.port.in.command.UpdateProfileImagePathCommand;
import com.wom.ttchat.member.domain.Member;

public interface UpdateMemberUserCase {
	void registerMember(MemberCommand memberCommand);
	void updateMember(MemberCommand memberCommand);
	void updateProfileImagePath(UpdateProfileImagePathCommand updateProfileImagePathCommand);

}
