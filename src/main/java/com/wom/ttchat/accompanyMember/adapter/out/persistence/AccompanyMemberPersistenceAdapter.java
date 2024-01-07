package com.wom.ttchat.accompanyMember.adapter.out.persistence;

import com.wom.ttchat.accompanyMember.application.port.out.FindAccompanyMemberListPort;
import com.wom.ttchat.common.annotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@PersistenceAdapter
class AccompanyMemberPersistenceAdapter implements FindAccompanyMemberListPort {

  private final AccompanyMemberJpaRepository accompanyMemberJpaRepository;

  @Override
  public boolean isAccompanyMember(Long accompanyId, UUID memberId) throws Exception {
    return accompanyMemberJpaRepository.findByAccompany_AccompanyIdAndMember_Id(accompanyId, memberId).isPresent();
  }
}

