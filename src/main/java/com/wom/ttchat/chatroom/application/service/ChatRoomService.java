package com.wom.ttchat.chatroom.application.service;

import com.wom.ttchat.chatroom.adapter.in.web.reqeust.ChatRequest;
import com.wom.ttchat.accompany.application.port.out.LoadAccompanyPort;
import com.wom.ttchat.accompany.domain.Accompany;
import com.wom.ttchat.chatroom.adapter.in.web.response.ChatRoomResponse;
import com.wom.ttchat.chatroom.application.port.in.Command.CreateChatRoomCommand;
import com.wom.ttchat.chatroom.application.port.in.Command.EnterChatRoomCommand;
import com.wom.ttchat.chatroom.application.port.in.Command.QuitChatRoomCommand;
import com.wom.ttchat.chatroom.application.port.in.CreateChatRoomUseCase;
import com.wom.ttchat.chatroom.application.port.in.EnterChatRoomUseCase;
import com.wom.ttchat.chatroom.application.port.in.LoadChatRoomUseCase;
import com.wom.ttchat.chatroom.application.port.in.QuitChatRoomUseCase;
import com.wom.ttchat.chatroom.application.port.out.FindChatRoomPort;
import com.wom.ttchat.chatroom.application.port.out.SaveChatRoomPort;
import com.wom.ttchat.chatroom.domain.ChatRoom;
import com.wom.ttchat.chatroom.domain.ChatRoomInfo;
import com.wom.ttchat.chatroom.domain.ChatStat;
import com.wom.ttchat.common.CommonCode;
import com.wom.ttchat.common.Utils.CommonUtils;
import com.wom.ttchat.common.dto.PageRequest;
import com.wom.ttchat.member.application.port.out.LoadMemberPort;
import com.wom.ttchat.member.domain.Member;
import com.wom.ttchat.member.domain.Member.MemberId;
import com.wom.ttchat.message.adapter.in.request.MessageRequest;
import com.wom.ttchat.message.application.WSMessageService;
import com.wom.ttchat.message.application.port.out.FindMessagePort;
import com.wom.ttchat.message.domain.Message;
import com.wom.ttchat.message.domain.MessageType;
import com.wom.ttchat.message.domain.SystemMessage;
import com.wom.ttchat.participant.application.port.out.FindParticipantPort;
import com.wom.ttchat.participant.application.port.out.UpdateParticipantPort;
import com.wom.ttchat.participant.domain.Participant;
import com.wom.ttchat.participant.domain.ParticipantStatus;

import java.util.*;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ChatRoomService implements EnterChatRoomUseCase, LoadChatRoomUseCase, QuitChatRoomUseCase, CreateChatRoomUseCase {
    private final LoadMemberPort loadMemberPort;
    private final UpdateParticipantPort updateParticipantPort;
    private final FindChatRoomPort findChatRoomPort;
    private final FindParticipantPort findParticipantPort;
    private final FindMessagePort findMessagePort;
    private final SaveChatRoomPort saveChatRoomPort;
    private final LoadAccompanyPort loadAccompanyPort;
    private final WSMessageService wsMessageService;

    @Override
    public Participant enterChatRoom(EnterChatRoomCommand command) throws Exception{
        Member member = loadMemberPort.loadMember(command.getMemberId());
        Participant participant = Participant.builder()
                .participantId(new Participant.ParticipantId(UUID.randomUUID()))
                .member(member)
                .chatRoom(findChatRoomPort.findByUid(command.getRoomId()))
                .status(ParticipantStatus.JOINED)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        Participant joined = findParticipantPort.findParticipantByRoomIdAndMemberId(participant.getChatRoom(), member.getId());
        Participant updatedParticipant = participant;
        if (joined != null && joined.isJoined()) {
            throw new IllegalStateException("이미 참여 중인 채팅방입니다.");
        } else if (joined != null && joined.isBanned()) {
            throw new IllegalStateException("강퇴된 채팅방에는 다시 참여할 수 없습니다.");
        } else if (joined != null && joined.isLeft()) {
            updateParticipantPort.updateParticipantStatus(participant);
        } else if (joined == null) {
            updatedParticipant = updateParticipantPort.updateParticipant(participant);
        }
        return updatedParticipant;
    }

    @Override
    public Participant transactionalEnterChatRoom(EnterChatRoomCommand command) throws Exception {
        Participant participant = enterChatRoom(command);
        wsMessageService.saveMessage(MessageRequest.builder()
                .roomId(command.getRoomId().toString())
                .content(participant.getMember().getNickname() + SystemMessage.ENTER.getMsg())
                .messageType(MessageType.SYS)
                .nickname(participant.getMember().getNickname())
                .senderId(command.getMemberId().toString())
                .build());
        return participant;
    }

    @Override
    public ChatRoomResponse loadChatRoom(UUID chatRoomUId) throws Exception {
        return null;
    }

    @Override
    public List<ChatRoomInfo> loadChatRoomList(MemberId memberId, PageRequest pageRequest) throws Exception {
        List<ChatRoomInfo> chatRoomInfoList = findChatRoomPort.findChatRoomListByMemberId(memberId, pageRequest);

        for(ChatRoomInfo info : chatRoomInfoList) {
            ChatRoom chatRoom = findChatRoomPort.findByUid(info.getRoomUid());

            Participant participantInfo = findParticipantPort.findParticipantByRoomIdAndMemberId(chatRoom, memberId);

            if (participantInfo==null)
                throw new IllegalStateException(CommonCode.UNAUTHORIZED_ACCESS_CHATROOM.getMessage());

            //마지막으로 읽은 메세지 정보 추가
            LocalDateTime readAt = participantInfo.getReadAt();
            //참여자테이블에 읽은 시간 데이터가 없을 경우 참여한 시간 이후 메세지 조회
            if(CommonUtils.isEmpty(readAt)) {
                //update로 하는 이유: 나갔다 들어왔을 때 대비
                if (!CommonUtils.isEmpty(participantInfo.getUpdatedAt()))
                    readAt = participantInfo.getUpdatedAt();
                else
                    readAt = LocalDateTime.now();
            }

            List<Message> messageList =
                findMessagePort.findUnReadMessage(info.getRoomUid(), readAt.plusHours(9));
            if(messageList.size()==0)
                info.updateMessage(null, null, 0);
            else {
                //채팅방의 마지막 메세지가 내가 보낸 메세지이면 안읽은개수 0
                if (messageList.get(0).getMemberId().equals(memberId.getValue().toString()))
                    info.updateMessage(messageList.get(0).getContent(), messageList.get(0).getCreateAt(), 0);
                else
                    info.updateMessage(messageList.get(0).getContent(), messageList.get(0).getCreateAt(), messageList.size());
            }

            //참여자 추가
            List<Participant> participantList = findParticipantPort.findJoinParticipantListByRoomId(chatRoom);

            List<Member> members = new ArrayList<>();
            for (Participant participant : participantList) {
                members.add(participant.getMember());
            }
            info.updateParticipantList(members);

        }

        return chatRoomInfoList;
    }


    @Override
    public Participant quitChatRoom(QuitChatRoomCommand command) throws Exception{
        Member member = loadMemberPort.loadMember(command.getMemberId());
        Participant participant = findParticipantPort.findParticipantByRoomIdAndMemberId(
                findChatRoomPort.findByUid(command.getRoomId()),
                member.getId());
        if (participant.isJoined()) {
            participant.setStatus(ParticipantStatus.LEFT);
            updateParticipantPort.updateParticipantStatus(participant);
        } else {
            throw new IllegalStateException("참여중인 채팅방에서만 퇴장이 가능합니다.");
        }
        return participant;
    }

    @Override
    @Transactional
    public Participant transactionalQuiChatRoom(QuitChatRoomCommand command) throws Exception {
        Participant participant = quitChatRoom(command);
        wsMessageService.saveMessage(MessageRequest.builder()
                .roomId(command.getRoomId().toString())
                .content(participant.getMember().getNickname() + SystemMessage.QUIT.getMsg())
                .messageType(MessageType.SYS)
                .nickname(participant.getMember().getNickname())
                .senderId(command.getMemberId().toString())
                .build());
        return participant;
    }


    @Override
    public ChatRoom createRoom(CreateChatRoomCommand command) throws Exception {
        Member member = loadMemberPort.loadMember(command.getHostId());

        Accompany accompany = null;
        String room_name = command.getName();
        if (command.isGroup()) {
            accompany = loadAccompanyPort.loadAccompany(command.getAccompany_post_id());

            if(CommonUtils.isEmpty(room_name))
                room_name = accompany.getPostTitle();
        }
        if (command.getAccompany_post_id() != null
            && findChatRoomPort.findByAccompanyPostId(command.getAccompany_post_id()) != null) {
            throw new IllegalStateException("동행글 당 하나의 채팅방만 생성이 가능합니다.");
        }
        ChatRoom chatRoom = ChatRoom.builder()
                .accompanyPostId(command.getAccompany_post_id())
                .chatRoomUUID(UUID.randomUUID())
                .createdAt(LocalDateTime.now())
                .hostMemberId(member)
                .name(room_name)
                .isGroup(command.isGroup())
                .build();
        return saveChatRoomPort.saveChatRoom(chatRoom);
    }

    @Override
    @Transactional
    public ChatRoom transactionalCreateAccompanyRoom(ChatRequest req, UUID hostId) throws Exception {
        ChatRoom chatRoom = createRoom(new CreateChatRoomCommand(
                new MemberId(hostId), req.getName(), ChatStat.GROUP.getStat(), req.getAccompanyPostId()
        ));
        enterChatRoom(new EnterChatRoomCommand(
                new MemberId(hostId), chatRoom.getChatRoomUUID()
        ));
        return chatRoom;
    }

    @Override
    @Transactional
    public ChatRoom transactionalCreateDirectRoom(ChatRequest req, UUID hostId) throws Exception{

        ChatRoom chatRoom = createRoom(new CreateChatRoomCommand(
                new MemberId(hostId), req.getName(), ChatStat.DIRECT.getStat(), req.getAccompanyPostId()
        ));

        if(req.getAccompanyPostId() == null && existDirectRoomByHostAndGuestId(hostId, req.getMemberId())){
           throw new IllegalStateException("이미 만들어진 상대방과의 개인 채팅방이 있습니다.");
        }

        enterChatRoom(new EnterChatRoomCommand(
                new MemberId(hostId), chatRoom.getChatRoomUUID()
        ));
        enterChatRoom(new EnterChatRoomCommand(
                new MemberId(req.getMemberId()), chatRoom.getChatRoomUUID()
        ));

        return chatRoom;
    }

    @Override
    public boolean existDirectRoomByHostAndGuestId(UUID hostId, UUID guestId) throws Exception {
        return findChatRoomPort.isExistDirectRoomByHostAndGuestId(hostId, guestId);
    }
}
