package com.wom.ttchat.accompany.adapter.out.persistence;

import com.wom.ttchat.accompany.application.port.out.LoadAccompanyPort;
import com.wom.ttchat.accompany.application.port.out.UpdateAccompanyPort;
import com.wom.ttchat.accompany.domain.Accompany;
import com.wom.ttchat.common.annotation.PersistenceAdapter;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
public class AccompanyPersistenceAdapter implements UpdateAccompanyPort, LoadAccompanyPort {

	private final AccompanyJpaRepository accompanyJpaRepository;
	private final AccompanyMapper accompanyMapper;

	@Override
	public void updateAccompany(Accompany accompany) {
		accompanyJpaRepository.save(accompanyMapper.mapToJpaEntity(accompany));
	}

	@Override
	public Accompany loadAccompany(Long accompanyId) {
		AccompanyJpaEntity jpaEntity = accompanyJpaRepository.findById(
			accompanyId).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 데이터입니다."));

		return accompanyMapper.mapToDomainEntity(jpaEntity);
	}
}
