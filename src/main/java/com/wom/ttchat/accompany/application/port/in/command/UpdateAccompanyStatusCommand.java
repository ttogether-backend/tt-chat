package com.wom.ttchat.accompany.application.port.in.command;

import com.wom.ttchat.accompany.domain.AccompanyStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UpdateAccompanyStatusCommand {
	private Long accompanyId;
	private AccompanyStatus accompanyStatus;
}
