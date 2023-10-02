package com.wom.ttchat.member.adapter.out.persistence;

import com.wom.ttchat.chatroom.adapter.out.entity.ChatRoomJpaEntity;
import com.wom.ttchat.member.domain.AccountStatus;
import com.wom.ttchat.member.domain.CertificationStatus;
import com.wom.ttchat.member.domain.Member;
import com.wom.ttchat.member.domain.Member.MemberId;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MemberMapper {

	public Member mapToDomainEntity(MemberJpaEntity jpaEntity) {
		return Member.generateMember(
			new MemberId(jpaEntity.getId()),
			jpaEntity.getNickname(),
			jpaEntity.getProfileImagePath(),
			jpaEntity.getBio(),
			CertificationStatus.valueOf(jpaEntity.getCertificationStatus()),
			AccountStatus.valueOf(jpaEntity.getAccountStatus()));
	}

	public MemberJpaEntity mpaToJpaEntity(Member member) {
		return new MemberJpaEntity(
			member.getId().getValue(),
			member.getNickname(),
			member.getProfileImagePath(),
			member.getBio(),
			member.getCertificationStatus().name(),
			member.getAccountStatus().name()
		);
	}

}
