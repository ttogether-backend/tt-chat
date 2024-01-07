package com.wom.ttchat.common.handler;

import com.wom.ttchat.message.application.port.in.MessageUseCase;
import com.wom.ttchat.participant.application.port.in.RegisterParticipantUseCase;
import com.wom.ttchat.participant.infrastructure.adapter.in.request.ParticipantRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.PingMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RequiredArgsConstructor
@Component
public class WebSockHandler extends TextWebSocketHandler {

    private final Map<WebSocketSession, String> sessionUserMap = new ConcurrentHashMap<>();
    // private final SimpMessagingTemplate messagingTemplate;

    @Override
    public void afterConnectionEstablished(@NonNull WebSocketSession session) throws Exception {
        log.info("*********afterConnectionEstablished*********");
         Map<String, List<String>> handshakeHeaders = session.getHandshakeHeaders();

        String memberId = extractDataFromSession(handshakeHeaders, "member");
        String roomId = extractDataFromSession(handshakeHeaders, "room");
        String nickname = extractDataFromSession(handshakeHeaders, "name");
        // 클라이언트가 연결되면 세션을 맵에 추가
        sessionUserMap.put(session, memberId);

        // messagingTemplate.convertAndSendToUser(session.getId(), "/sub/room"+roomId, new TextMessage("입장했습니다."));


    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        JSONObject jsonObject = new JSONObject(payload);
        String user = sessionUserMap.get(session); // 세션에 연결된 사용자 이름 가져오기
        String content = jsonObject.getString("content");

        // 모든 연결된 클라이언트에게 메시지 전송
        for (WebSocketSession s : sessionUserMap.keySet()) {
            s.sendMessage(new TextMessage(user + ": " + content));
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // 세션 연결이 종료되면 맵에서 세션 제거
        sessionUserMap.remove(session);
    }

    private String extractDataFromSession(Map<String, List<String>> handshakeHeaders, String key) throws IllegalStateException {
        if (handshakeHeaders.containsKey(key)) {
            List<String> values = handshakeHeaders.get(key);

            if (!values.isEmpty()) {
                return values.get(0);
            } else {
                throw new IllegalStateException( key + "값이 비워져있습니다.");
            }
        } else {
            throw new IllegalStateException("필수값 '" + key + "이 없습니다.");
        }
    }

}
