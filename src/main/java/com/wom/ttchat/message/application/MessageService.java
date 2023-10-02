package com.wom.ttchat.message.application;

import com.wom.ttchat.chatroom.application.port.out.FindChatRoomPort;
import com.wom.ttchat.chatroom.domain.ChatRoom;
import com.wom.ttchat.common.Utils.CommonUtils;
import com.wom.ttchat.common.annotation.UseCase;
import com.wom.ttchat.member.domain.Member.MemberId;
import com.wom.ttchat.message.adapter.out.persistence.MessageJpaEntity;
import com.wom.ttchat.message.adapter.out.persistence.MessageJpaRepository;
import com.wom.ttchat.message.application.port.in.MessageUseCase;
import com.wom.ttchat.message.application.port.out.FindMessagePort;
import com.wom.ttchat.message.domain.Message;
import com.wom.ttchat.participant.application.port.out.FindParticipantPort;
import com.wom.ttchat.participant.domain.Participant;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@UseCase
public class MessageService implements MessageUseCase {

    private final MessageJpaRepository messageJpaRepository;
    private final FindMessagePort findMessagePort;
    private final FindParticipantPort findParticipantPort;
    private final FindChatRoomPort findChatRoomPort;


    @Override
    public MessageJpaEntity saveMessage(MessageJpaEntity message) {

        return messageJpaRepository.insert(message);
    }

    @Override
    public List<Message> getMessageList(UUID roomId, MemberId memberId) throws Exception {
        ChatRoom chatRoom = findChatRoomPort.findByUid(roomId);

        Participant participant = findParticipantPort.findJoinParticipant(chatRoom, memberId);

        LocalDateTime readAt = participant.getReadAt();
        //참여자테이블에 읽은 시간 데이터가 없을 경우 참여한 시간 이후 메세지 조회
        if(CommonUtils.isEmpty(readAt)) {
            //update로 하는 이유: 나갔다 들어왔을 때 대비
            if (!CommonUtils.isEmpty(participant.getUpdatedAt()))
                readAt = participant.getUpdatedAt();
            else
                readAt = LocalDateTime.now();
        }

        List<Message> messageList = findMessagePort.findMessageList(roomId, readAt.plusHours(9));
        if (CommonUtils.isEmpty(messageList))
            messageList = null;

        return messageList;

    }


}
