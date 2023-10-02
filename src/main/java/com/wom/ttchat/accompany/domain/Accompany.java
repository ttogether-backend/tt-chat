package com.wom.ttchat.accompany.domain;

import com.wom.ttchat.member.domain.Member.MemberId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Accompany {
	private final Long accompanyId;
	private final Long accompanyPostId;
	private AccompanyStatus status;
	private String postTitle;
	private MemberId hostId;

	public static Accompany generateAccompany(
		Long accompanyId, Long accompanyPostId,
		AccompanyStatus status, String postTitle, MemberId hostId) {
		return new Accompany(
			accompanyId,
			accompanyPostId,
			status,
			postTitle,
			hostId
		);
	}

	public void updateAccompanyStatus(AccompanyStatus status) {
		this.status = status;
	}

	public void updateAccompanyPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
}
