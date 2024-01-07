package com.wom.ttchat.member.application.port.out;

import com.wom.ttchat.member.domain.Member;

public interface UpdateMemberPort {
	void updateMember(Member member);
	void createMember(Member member) throws Exception;

}
