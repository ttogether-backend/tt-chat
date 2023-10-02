package com.wom.ttchat.member.application.port.out;


import com.wom.ttchat.member.domain.Member;
import com.wom.ttchat.member.domain.Member.MemberId;

public interface LoadMemberPort {

  Member loadMember(MemberId memberId);
}
