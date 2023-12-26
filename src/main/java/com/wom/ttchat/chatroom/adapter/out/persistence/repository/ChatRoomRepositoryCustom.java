package com.wom.ttchat.chatroom.adapter.out.persistence.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.wom.ttchat.chatroom.domain.ChatRoomInfo;
import com.wom.ttchat.common.dto.PageRequest;
import com.wom.ttchat.member.domain.Member.MemberId;
import java.util.List;
import java.util.UUID;

public interface ChatRoomRepositoryCustom {
	List<ChatRoomInfo> findChatRoomListByMemberId(MemberId memberId, PageRequest pageRequest);

	UUID isExistDirectRoomByHostAndGuestId(UUID hostId, UUID guestId);

}
