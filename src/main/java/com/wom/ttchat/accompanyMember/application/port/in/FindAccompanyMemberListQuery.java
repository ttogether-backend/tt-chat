package com.wom.ttchat.accompanyMember.application.port.in;


import java.util.UUID;

public interface FindAccompanyMemberListQuery {

  boolean isAccompanyMember(Long accompanyId, UUID memberId) throws Exception;
}
