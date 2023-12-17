package com.wom.ttchat.chatroom.domain;

import com.wom.ttchat.member.domain.Member;
import java.time.LocalDateTime;
import java.util.List;

import com.wom.ttchat.participant.domain.Participant;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
public class ChatRoomInfo {
	//Long roomId;
	UUID roomUid;
	String chatRoomTitle;
	String accompanyStatus;
	String accompanyPostTitle;
	List<Member> participantList;
	int participantCnt;
	String lastMessage;
	LocalDateTime lastMessageAt;
	long notReadCnt;

	public ChatRoomInfo (UUID roomUid, String chatRoomTitle, String accompanyStatus, String accompanyPostTitle) {
		this.roomUid = roomUid;
		this.chatRoomTitle = chatRoomTitle;
		this.accompanyStatus = accompanyStatus;
		this.accompanyPostTitle = accompanyPostTitle;
	}

	public void updateParticipantList(List<Member> participantList) {
		this.participantList = participantList;
		this.participantCnt = participantList.size();
	}

	public void updateMessage(String lastMessage, LocalDateTime lastMessageAt, long notReadCnt) {
		this.lastMessage = lastMessage;
		this.lastMessageAt = lastMessageAt;
		this.notReadCnt = notReadCnt;
	}
}
