package com.wom.ttchat.message.adapter.out.persistence;

import com.wom.ttchat.common.annotation.PersistenceAdapter;
import com.wom.ttchat.message.application.port.out.FindMessagePort;
import com.wom.ttchat.message.application.port.out.MessagePostPort;
import com.wom.ttchat.message.domain.Message;
import com.wom.ttchat.message.domain.MessageType;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
public class MessagePersistenceAdapter implements MessagePostPort, FindMessagePort {

    private final MessageJpaRepository messageJpaRepository;
    private final MessageMapper messageMapper;

    @Override
    public MessageJpaEntity saveMessage(MessageJpaEntity messageJpaEntity) throws Exception{
        MessageJpaEntity savedMessage = messageJpaRepository.save(messageJpaEntity);
        return savedMessage;
    }

    @Override
    public List<Message> findUnReadMessage(UUID roomUid, LocalDateTime readAt) {
        List< MessageType> typeList = new ArrayList<>();
        typeList.add(MessageType.MSG);
        typeList.add(MessageType.MAP);

        List<MessageJpaEntity> jpaEntityList =
            messageJpaRepository.findByRoomUIdAndCreateAtAfterAndTypeInOrderByCreateAtDesc(
                roomUid.toString(), readAt, typeList);

        List<Message> messageList = new ArrayList<>();
        for (MessageJpaEntity jpaEntity : jpaEntityList) {
            messageList.add(messageMapper.mapToDomainEntity(jpaEntity));
        }

        return messageList;
    }

    @Override
    public List<Message> findReadMessages(UUID roomUid, LocalDateTime readAt) {
        List<MessageJpaEntity> jpaEntityList =
                messageJpaRepository.findByRoomUIdAndCreateAtBeforeOrderByCreateAt(roomUid.toString(), readAt);
        return jpaEntityList.stream()
                .map(messageMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Message> findUnReadMessages(UUID roomUid, LocalDateTime readAt) {
        List<MessageJpaEntity> jpaEntityList =
            messageJpaRepository.findByRoomUIdAndCreateAtAfterOrderByCreateAt(roomUid.toString(), readAt);
        return jpaEntityList.stream()
                .map(messageMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }


    @Override
    public List<Message> findBanBeforeUnReadMessage(UUID roomUid, LocalDateTime readAt,
        LocalDateTime banAt) {
        List< MessageType> typeList = new ArrayList<>();
        typeList.add(MessageType.MSG);
        typeList.add(MessageType.MAP);

        List<MessageJpaEntity> jpaEntityList =
            messageJpaRepository.findByRoomUIdAndCreateAtBetweenAndTypeInOrderByCreateAtDesc(
                roomUid.toString(), readAt, banAt, typeList);

        List<Message> messageList = new ArrayList<>();
        for (MessageJpaEntity jpaEntity : jpaEntityList) {
            messageList.add(messageMapper.mapToDomainEntity(jpaEntity));
        }

        return messageList;
    }

    @Override
    public List<Message> findBanBeforeUnReadMessageList(UUID roomUid, LocalDateTime readAt,
        LocalDateTime banAt) {
        List<MessageJpaEntity> jpaEntityList =
            messageJpaRepository.findByAndRoomUIdAndCreateAtBetweenOrderByCreateAt(roomUid.toString(), readAt, banAt);

        List<Message> messageList = new ArrayList<>();
        for (MessageJpaEntity jpaEntity : jpaEntityList) {
            messageList.add(messageMapper.mapToDomainEntity(jpaEntity));
        }
        return messageList;
    }

    @Override
    public long findUnReadMessagesCount(UUID roomUid, LocalDateTime readAt) {
        return messageJpaRepository.countByRoomUIdAndCreateAtAfter(roomUid.toString(), readAt);
    }

    @Override
    public Message findLastSentMessage(UUID roomUid) {
        MessageJpaEntity messageJpaEntity = messageJpaRepository.findTopByRoomUIdOrderByCreateAtDesc(roomUid.toString());
        return messageJpaEntity != null ? messageMapper.mapToDomainEntity(
                messageJpaRepository.findTopByRoomUIdOrderByCreateAtDesc(roomUid.toString())) : null;
    }


}
