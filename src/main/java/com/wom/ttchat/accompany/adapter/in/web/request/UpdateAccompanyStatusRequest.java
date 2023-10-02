package com.wom.ttchat.accompany.adapter.in.web.request;

import lombok.Getter;

@Getter
public class UpdateAccompanyStatusRequest {
	Long accompanyId;
	String status;
}
