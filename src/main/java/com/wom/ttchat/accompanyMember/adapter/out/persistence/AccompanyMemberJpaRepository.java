package com.wom.ttchat.accompanyMember.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccompanyMemberJpaRepository extends JpaRepository<AccompanyMemberJpaEntity, Long>{

  Optional<AccompanyMemberJpaEntity> findByAccompany_AccompanyIdAndMember_Id(Long accompanyId,UUID memberId) throws Exception;

}
