package com.wom.ttchat.member.adapter.out.persistence;

import com.wom.ttchat.member.domain.AccountStatus;
import com.wom.ttchat.member.domain.CertificationStatus;
import com.wom.ttchat.member.domain.Member;
import com.wom.ttchat.member.domain.Member.MemberId;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

	public Member mapToDomainEntity(MemberJpaEntity jpaEntity) {
		if (jpaEntity == null)
			return null;
		return Member.generateMember(
			new MemberId(jpaEntity.getId()),
			jpaEntity.getNickname(),
			jpaEntity.getProfileImagePath(),
			jpaEntity.getBio(),
			CertificationStatus.valueOf(jpaEntity.getCertificationStatus()),
			AccountStatus.valueOf(jpaEntity.getAccountStatus()));
	}

	public MemberJpaEntity mapToJpaEntity(Member member) {
		if (member == null)
			return null;
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
