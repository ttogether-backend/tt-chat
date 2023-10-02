package com.wom.ttchat.accompany.adapter.in.web;

import com.wom.ttchat.accompany.adapter.in.web.request.RegisterAccompanyRequest;
import com.wom.ttchat.accompany.adapter.in.web.request.UpdateAccompanyStatusRequest;
import com.wom.ttchat.accompany.application.port.in.UpdateAccompanyUseCase;
import com.wom.ttchat.accompany.application.port.in.command.RegisterAccompanyCommand;
import com.wom.ttchat.accompany.application.port.in.command.UpdateAccompanyPostTitleCommand;
import com.wom.ttchat.accompany.application.port.in.command.UpdateAccompanyStatusCommand;
import com.wom.ttchat.accompany.domain.AccompanyStatus;
import com.wom.ttchat.common.ApiResponse;
import com.wom.ttchat.common.ApiUtils;
import com.wom.ttchat.common.annotation.WebAdapter;
import com.wom.ttchat.member.domain.Member.MemberId;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chat")
public class AccompanyController {

	private final UpdateAccompanyUseCase updateAccompanyUseCase;

	@PostMapping("/accompany")
	public ApiResponse<?> createAccompany(@RequestBody RegisterAccompanyRequest request) {
		RegisterAccompanyCommand registerAccompanyCommand = new RegisterAccompanyCommand(
			request.getAccompanyId(),
			request.getAccompanyPostId(),
			AccompanyStatus.valueOf(request.getAccompanyStatus()),
			request.getPostTitle(),
			new MemberId(request.getHostId())
		);

		updateAccompanyUseCase.registerAccompany(registerAccompanyCommand);

		return ApiUtils.successCreateWithEmptyResponse();
	}

	@PutMapping("/accompany/status")
	public  ApiResponse<?> updateAccmpanyStatus(@RequestBody UpdateAccompanyStatusRequest request) {
		UpdateAccompanyStatusCommand updateAccompanyStatusCommand = new UpdateAccompanyStatusCommand(
			request.getAccompanyId(),
			AccompanyStatus.valueOf(request.getStatus())
		);

		updateAccompanyUseCase.updateAccompanyStatus(updateAccompanyStatusCommand);

		return ApiUtils.successWithEmptyResponse();
	}

	@PutMapping("/accompany/post-title")
	public  ApiResponse<?> updateAccmpanyPostTitle(@RequestBody UpdateAccompanyPostTitleCommand request) {
		UpdateAccompanyPostTitleCommand updateAccompanyPostTitleCommand = new UpdateAccompanyPostTitleCommand(
			request.getAccompanyId(),
			request.getPostTitle()
		);

		updateAccompanyUseCase.updateAccompanyPostTitle(updateAccompanyPostTitleCommand);

		return ApiUtils.successWithEmptyResponse();
	}
}
