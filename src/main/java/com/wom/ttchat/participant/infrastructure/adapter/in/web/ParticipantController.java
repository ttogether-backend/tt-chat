package com.wom.ttchat.participant.infrastructure.adapter.in.web;


import com.wom.ttchat.common.ApiResponse;
import com.wom.ttchat.common.ApiUtils;
import com.wom.ttchat.common.annotation.WebAdapter;
import com.wom.ttchat.participant.application.port.in.FindParticipantUseCase;
import com.wom.ttchat.participant.domain.Participant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chat/participant")
public class ParticipantController {

    private final FindParticipantUseCase findParticipantUseCase;


    @GetMapping("/confirm/{roomId}")
    public ApiResponse<Boolean> authorityEnterRoom(@RequestHeader("memberId") UUID memberId, @PathVariable String roomId) throws Exception{
        UUID roomUId = UUID.fromString(roomId);
        Boolean isAuth = findParticipantUseCase.isParticipant(roomUId, memberId);
        return ApiUtils.createSuccessWithDataResponse(isAuth);
    }

}



