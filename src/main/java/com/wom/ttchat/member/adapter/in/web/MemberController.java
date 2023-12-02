package com.wom.ttchat.member.adapter.in.web;

import com.wom.ttchat.common.ApiResponse;
import com.wom.ttchat.common.ApiUtils;
import com.wom.ttchat.common.annotation.WebAdapter;
import com.wom.ttchat.member.adapter.in.web.request.RegisterMemberRequest;
import com.wom.ttchat.member.adapter.in.web.request.UpdateImageRequest;
import com.wom.ttchat.member.adapter.in.web.request.UpdateMemberRequest;
import com.wom.ttchat.member.application.port.in.UpdateMemberUserCase;
import com.wom.ttchat.member.application.port.in.command.MemberCommand;
import com.wom.ttchat.member.application.port.in.command.UpdateProfileImagePathCommand;
import com.wom.ttchat.member.domain.AccountStatus;
import com.wom.ttchat.member.domain.CertificationStatus;
import com.wom.ttchat.member.domain.Member.MemberId;
import java.util.UUID;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@Tag(name = "회원", description = "회원 정보 수정, 회원 조회, 회원 프로필 조회")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chat")
public class MemberController {

	private final UpdateMemberUserCase updateMemberUserCase;

	@PostMapping(path = "/member")
	public ApiResponse<?> createMember(@RequestBody RegisterMemberRequest request) {
		MemberCommand memberCommand = new MemberCommand(
			new MemberId(request.getMemberId()),
			request.getNickname(),
			request.getBio(),
			CertificationStatus.valueOf(request.getCertificationStatus()),
			AccountStatus.valueOf(request.getAccountStatus())
		);

		updateMemberUserCase.registerMember(memberCommand);

		return ApiUtils.successCreateWithEmptyResponse();
	}

	@PutMapping(path = "/member")
	public ApiResponse<?> updateMember(@RequestHeader("memberId") UUID memberId, @RequestBody UpdateMemberRequest request) {
		MemberCommand memberCommand = new MemberCommand(
			new MemberId(memberId),
			request.getNickname(),
			request.getBio(),
			CertificationStatus.valueOf(request.getCertificationStatus()),
			AccountStatus.valueOf(request.getAccountStatus())
		);

		updateMemberUserCase.updateMember(memberCommand);

		return ApiUtils.successWithEmptyResponse();
	}

	@PutMapping(path = "/member/profile")
	public ApiResponse<?> updateMemberProfileImaginePath(@RequestHeader("memberId") UUID memberId, @RequestBody UpdateImageRequest request) {

		UpdateProfileImagePathCommand updateProfileImagePathCommand = new UpdateProfileImagePathCommand(
			new MemberId(memberId),
			request.getProfileImagePath()
		);

		updateMemberUserCase.updateProfileImagePath(updateProfileImagePathCommand);

		return ApiUtils.successWithEmptyResponse();
	}


}
