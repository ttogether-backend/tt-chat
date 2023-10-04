package com.wom.ttchat.accompanyMember.application.port.out;


import java.util.UUID;

public interface FindAccompanyMemberListPort {

    boolean isAccompanyMember(Long accompanyId, UUID memberId) throws Exception;

}
