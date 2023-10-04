package com.wom.ttchat.accompanyMember.adapter.in.web.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

import java.util.UUID;

@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
@Getter
public class MemberSummary {
  private final UUID memberId;
  private final String nickname;
  private final String bio;
  private final String certificationStatus;
  private final String profileImagePath;
  private String profileImageUrl;
  private final int completedAccompanyCount;

  public MemberSummary(UUID memberId, String nickname, String bio, String certificationStatus,
                       String profileImagePath, int completedAccompanyCount) {
    this.memberId = memberId;
    this.nickname = nickname;
    this.bio = bio;
    this.certificationStatus = certificationStatus;
    this.profileImagePath = profileImagePath;
    this.completedAccompanyCount = completedAccompanyCount;
  }

  public void updateProfileImageUrl(String imageUrl) {
    this.profileImageUrl = imageUrl;
  }
}
