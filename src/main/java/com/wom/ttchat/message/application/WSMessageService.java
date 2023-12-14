package com.wom.ttchat.message.application;

import com.wom.ttchat.chatroom.application.port.out.FindChatRoomPort;
import com.wom.ttchat.chatroom.domain.ChatRoom;
import com.wom.ttchat.common.CommonCode;
import com.wom.ttchat.common.Utils.CommonUtils;
import com.wom.ttchat.common.annotation.UseCase;
import com.wom.ttchat.member.domain.Member.MemberId;
import com.wom.ttchat.message.adapter.in.request.MessageRequest;
import com.wom.ttchat.message.adapter.out.persistence.MessageJpaEntity;
import com.wom.ttchat.message.adapter.out.persistence.MessageJpaRepository;
import com.wom.ttchat.message.adapter.out.persistence.MessageMapper;
import com.wom.ttchat.message.application.port.in.MessageUseCase;
import com.wom.ttchat.message.application.port.in.WSMessageUseCase;
import com.wom.ttchat.message.application.port.out.FindMessagePort;
import com.wom.ttchat.message.application.port.out.MessagePostPort;
import com.wom.ttchat.message.domain.Message;
import com.wom.ttchat.participant.application.port.out.FindParticipantPort;
import com.wom.ttchat.participant.domain.Participant;
import jakarta.ws.rs.NotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

@Slf4j
@RequiredArgsConstructor
@UseCase
public class WSMessageService implements WSMessageUseCase {

    private final MessagePostPort messagePostPort;
    private final MessageMapper messageMapper;
    private final SimpMessageSendingOperations simpMessageSendingOperations;
    private final FindMessagePort findMessagePort;
    private final FindParticipantPort findParticipantPort;
    private final FindChatRoomPort findChatRoomPort;


    @Override
    public void saveMessage(MessageRequest messageReq) throws Exception {
        messagePostPort.saveMessage(messageMapper.mapRequestToJpaEntity(messageReq));
    }

    @Override
    public List<Message> getMessageList(UUID roomId, MemberId memberId) throws Exception{
        ChatRoom chatRoom = findChatRoomPort.findByUid(roomId);

        Participant participant = findParticipantPort.findParticipantByRoomIdAndMemberId(chatRoom, memberId);
        if (participant==null)
            throw new IllegalStateException(CommonCode.UNAUTHORIZED_ACCESS_CHATROOM.getMessage());

        LocalDateTime readAt = participant.getReadAt();
        //참여자테이블에 읽은 시간 데이터가 없을 경우 참여한 시간 이후 메세지 조회
        if(CommonUtils.isEmpty(readAt)) {
            if (!CommonUtils.isEmpty(participant.getCreatedAt()))
                readAt = participant.getCreatedAt();
            else
                readAt = LocalDateTime.now();
        }

        List<Message> messageList = findMessagePort.findUnReadMessageList(roomId, readAt.plusHours(9));
        if (CommonUtils.isEmpty(messageList))
            messageList = null;

        return messageList;
    }
}
