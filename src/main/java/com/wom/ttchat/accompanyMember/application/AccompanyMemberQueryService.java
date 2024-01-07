package com.wom.ttchat.accompanyMember.application;

import com.wom.ttchat.accompanyMember.application.port.in.FindAccompanyMemberListQuery;
import com.wom.ttchat.accompanyMember.application.port.out.FindAccompanyMemberListPort;
import com.wom.ttchat.common.annotation.UseCase;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@UseCase
public class AccompanyMemberQueryService implements FindAccompanyMemberListQuery {

  private final FindAccompanyMemberListPort findAccompanyMemberListPort;


  @Override
  public boolean isAccompanyMember(Long acconmpanyId, UUID memberId) throws Exception {
    return findAccompanyMemberListPort.isAccompanyMember(acconmpanyId, memberId);
  }
}
