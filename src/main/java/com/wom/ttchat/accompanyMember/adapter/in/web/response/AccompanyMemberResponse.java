package com.wom.ttchat.accompanyMember.adapter.in.web.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
@Getter
@AllArgsConstructor
public class AccompanyMemberResponse {
  int totalCount;
  List<AccompanyMemberSummary> memberList;
}
