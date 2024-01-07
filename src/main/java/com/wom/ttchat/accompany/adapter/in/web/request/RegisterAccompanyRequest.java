package com.wom.ttchat.accompany.adapter.in.web.request;

import java.util.UUID;
import lombok.Getter;

@Getter
public class RegisterAccompanyRequest {
	Long accompanyId;
	Long accompanyPostId;
	String accompanyStatus;
	String postTitle;
	UUID hostId;
}
