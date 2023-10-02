package com.wom.ttchat.accompany.adapter.out.persistence;

import com.wom.ttchat.accompany.domain.Accompany;
import com.wom.ttchat.accompany.domain.AccompanyStatus;
import com.wom.ttchat.member.domain.Member;
import com.wom.ttchat.member.domain.Member.MemberId;
import org.springframework.stereotype.Component;

@Component
public class AccompanyMapper {

	public Accompany mapToDomainEntity(AccompanyJpaEntity jpaEntity) {
		return Accompany.generateAccompany(
			jpaEntity.getAccompanyId(),
			jpaEntity.getAccompanyPostId(),
			AccompanyStatus.valueOf(jpaEntity.getStatus()),
			jpaEntity.getPostTitle(),
			new MemberId(jpaEntity.getHostId())
		);
	}

	public AccompanyJpaEntity mapToJpaEntity(Accompany accompany) {
		return new AccompanyJpaEntity(
			accompany.getAccompanyId(),
			accompany.getAccompanyPostId(),
			accompany.getStatus().name(),
			accompany.getPostTitle(),
			accompany.getHostId().getValue()
		);
	}

}
