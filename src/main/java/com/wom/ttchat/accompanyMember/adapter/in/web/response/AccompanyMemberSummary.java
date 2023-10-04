package com.wom.ttchat.accompanyMember.adapter.in.web.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
@Getter
@AllArgsConstructor
public class AccompanyMemberSummary {
  private final Long accompanyId;
  private final MemberSummary member;
  private final String status;
  private final String role;
}
