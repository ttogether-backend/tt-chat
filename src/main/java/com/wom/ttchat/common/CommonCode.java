package com.wom.ttchat.common;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonCode  {
    SUCCESS( "성공", HttpStatus.OK),

    FAILED_SYSTEM( "시스템 에러가 발생하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR ),

    NOT_FOUND_CHATROOM("존재하지 않는 채팅방입니다.", HttpStatus.BAD_REQUEST),

    UNAUTHORIZED_COMMAND_CHATROOM("채팅방 제어 권한이 없습니다.", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED_ACCESS_CHATROOM("채팅방 접근 권한이 없습니다.", HttpStatus.UNAUTHORIZED),

    KICK_OUT_CHATROOM("채팅방과 동행에서 퇴장되었습니다." , HttpStatus.UNAUTHORIZED),

    LOST_CONNECTION( "연결이 종료되었습니다. 다시 연결하려면 새로고침하세요.", null),

    GREETING( "{0}님이 채팅방에 입장하셨습니다 \uD83E\uDD73", null),

    ;

    private final String message;
    private final HttpStatus httpStatus;

}
