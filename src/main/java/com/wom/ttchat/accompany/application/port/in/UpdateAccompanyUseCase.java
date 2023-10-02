package com.wom.ttchat.accompany.application.port.in;

import com.wom.ttchat.accompany.application.port.in.command.RegisterAccompanyCommand;
import com.wom.ttchat.accompany.application.port.in.command.UpdateAccompanyPostTitleCommand;
import com.wom.ttchat.accompany.application.port.in.command.UpdateAccompanyStatusCommand;

public interface UpdateAccompanyUseCase {
	void registerAccompany(RegisterAccompanyCommand registerAccompanyCommand);
	void updateAccompanyStatus(UpdateAccompanyStatusCommand updateAccompanyStatusCommand);
	void updateAccompanyPostTitle(UpdateAccompanyPostTitleCommand updateAccompanyPostTitleCommand);
}
