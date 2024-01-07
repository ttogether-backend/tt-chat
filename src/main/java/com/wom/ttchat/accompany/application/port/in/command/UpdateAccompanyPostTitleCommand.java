package com.wom.ttchat.accompany.application.port.in.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UpdateAccompanyPostTitleCommand {
	private Long accompanyId;
	private String postTitle;
}
