package com.wom.ttchat.chatroom.application.port.out;

import com.wom.ttchat.chatroom.adapter.out.entity.ChatRoomJpaEntity;
import com.wom.ttchat.chatroom.domain.ChatRoom;

import com.wom.ttchat.chatroom.domain.ChatRoomInfo;
import com.wom.ttchat.common.dto.PageRequest;
import com.wom.ttchat.member.domain.Member;
import com.wom.ttchat.member.domain.Member.MemberId;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface FindChatRoomPort {

    ChatRoomJpaEntity findByUId(UUID chatRoomUId) throws Exception;
    ChatRoom findByUid(UUID chatRoomUID) throws Exception;
    List<ChatRoomInfo> findChatRoomListByMemberId(MemberId memberId, PageRequest pageRequest);
    ChatRoom findByAccompanyPostId(Long accompanyPostId);
    boolean isExistDirectRoomByHostAndGuestId(UUID hostId, UUID guestId);
}
