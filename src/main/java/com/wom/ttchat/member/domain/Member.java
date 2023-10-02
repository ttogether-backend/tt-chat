package com.wom.ttchat.member.domain;

import java.util.UUID;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Member {

	private final MemberId id;
	private String nickname;
	private String profileImagePath;
	private String bio;
	private CertificationStatus certificationStatus;
	private AccountStatus accountStatus;

	@Value
	public static class MemberId {
		UUID value;
	}

	public static Member generateMember(
		MemberId memberId, String nickname, String profileImagePath,
		String bio, CertificationStatus certificationStatus, AccountStatus accountStatus) {
		return new Member(
			memberId,
			nickname,
			profileImagePath,
			bio,
			certificationStatus,
			accountStatus);
	}

	public static Member create(MemberId memberId, String nickname,
		String bio, CertificationStatus certificationStatus, AccountStatus accountStatus) {
		return generateMember(
			memberId,
			nickname,
			null,
			bio,
			certificationStatus,
			accountStatus
		);
	}

	public boolean identifyById(MemberId id) {
		return this.id.equals(id);
	}

	public void updateMemberInfo(String nickname, String bio,
		CertificationStatus certificationStatus, AccountStatus accountStatus) {
		this.nickname = nickname;
		this.bio = bio;
		this.certificationStatus = certificationStatus;
		this.accountStatus = accountStatus;
	}

	public void updateProfileImagePath(String updateProfileImagePath) {
		this.profileImagePath = updateProfileImagePath;
	}

}
