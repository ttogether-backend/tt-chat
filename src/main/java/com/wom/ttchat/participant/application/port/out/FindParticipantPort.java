package com.wom.ttchat.participant.application.port.out;

import com.wom.ttchat.chatroom.domain.ChatRoom;
import com.wom.ttchat.member.domain.Member.MemberId;
import com.wom.ttchat.participant.domain.Participant;
import java.util.List;

public interface FindParticipantPort {
	List<Participant> findJoinParticipantListByRoomId(ChatRoom chatRoom);
	Participant findParticipantByRoomIdAndMemberId(ChatRoom chatRoom, MemberId memberId);
	Participant findJoinParticipant(ChatRoom chatRoom, MemberId memberId);
}
