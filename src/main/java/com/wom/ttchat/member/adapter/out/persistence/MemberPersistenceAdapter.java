package com.wom.ttchat.member.adapter.out.persistence;

import com.wom.ttchat.common.annotation.PersistenceAdapter;
import com.wom.ttchat.member.application.port.out.LoadMemberPort;
import com.wom.ttchat.member.application.port.out.UpdateMemberPort;
import com.wom.ttchat.member.domain.Member;
import com.wom.ttchat.member.domain.Member.MemberId;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
public class MemberPersistenceAdapter implements UpdateMemberPort, LoadMemberPort {

	private final MemberJpaRepository memberJpaRepository;
	private final MemberMapper memberMapper;

	@Override
	public void updateMember(Member member) {
		memberJpaRepository.save(memberMapper.mpaToJpaEntity(member));
	}

	@Override
	public Member loadMember(MemberId memberId) {
		MemberJpaEntity jpaEntity = memberJpaRepository.findById(
			memberId.getValue()).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 데이터입니다."));
		
		return memberMapper.mapToDomainEntity(jpaEntity);
	}
}
