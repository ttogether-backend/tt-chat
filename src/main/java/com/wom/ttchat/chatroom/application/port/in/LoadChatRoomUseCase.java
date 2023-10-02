package com.wom.ttchat.chatroom.application.port.in;

import com.wom.ttchat.chatroom.adapter.in.web.response.ChatRoomResponse;

import com.wom.ttchat.chatroom.domain.ChatRoomInfo;
import com.wom.ttchat.common.dto.PageRequest;
import com.wom.ttchat.member.domain.Member.MemberId;
import java.util.List;
import java.util.UUID;

public interface LoadChatRoomUseCase {

     ChatRoomResponse loadChatRoom(UUID chatRoomUId) throws Exception;
     List<ChatRoomInfo> loadChatRoomList(MemberId memberId, PageRequest pageRequest) throws Exception;

     boolean existDirectRoomByHostAndGuestId(UUID hostId, UUID guestId) throws Exception;

}
