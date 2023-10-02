package com.wom.ttchat.accompany.application;

import com.wom.ttchat.accompany.application.port.in.UpdateAccompanyUseCase;
import com.wom.ttchat.accompany.application.port.in.command.RegisterAccompanyCommand;
import com.wom.ttchat.accompany.application.port.in.command.UpdateAccompanyPostTitleCommand;
import com.wom.ttchat.accompany.application.port.in.command.UpdateAccompanyStatusCommand;
import com.wom.ttchat.accompany.application.port.out.LoadAccompanyPort;
import com.wom.ttchat.accompany.application.port.out.UpdateAccompanyPort;
import com.wom.ttchat.accompany.domain.Accompany;
import com.wom.ttchat.common.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@UseCase
public class AccompanyService implements UpdateAccompanyUseCase {

	private final UpdateAccompanyPort updateAccompanyPort;
	private final LoadAccompanyPort loadAccompanyPort;

	@Transactional
	@Override
	public void registerAccompany(RegisterAccompanyCommand registerAccompanyCommand) {
		Accompany accompany = Accompany.generateAccompany(
			registerAccompanyCommand.getAccompanyId(),
			registerAccompanyCommand.getAccompanyPostId(),
			registerAccompanyCommand.getAccompanyStatus(),
			registerAccompanyCommand.getPostTitle(),
			registerAccompanyCommand.getHostId()
		);

		updateAccompanyPort.updateAccompany(accompany);
	}

	@Transactional
	@Override
	public void updateAccompanyStatus(UpdateAccompanyStatusCommand updateAccompanyStatusCommand) {
		Accompany accompany = loadAccompanyPort.loadAccompany(updateAccompanyStatusCommand.getAccompanyId());
		accompany.updateAccompanyStatus(updateAccompanyStatusCommand.getAccompanyStatus());

		updateAccompanyPort.updateAccompany(accompany);
	}

	@Transactional
	@Override
	public void updateAccompanyPostTitle(UpdateAccompanyPostTitleCommand updateAccompanyPostTitleCommand) {
		Accompany accompany = loadAccompanyPort.loadAccompany(updateAccompanyPostTitleCommand.getAccompanyId());
		accompany.updateAccompanyPostTitle(updateAccompanyPostTitleCommand.getPostTitle());

		updateAccompanyPort.updateAccompany(accompany);
	}
}
