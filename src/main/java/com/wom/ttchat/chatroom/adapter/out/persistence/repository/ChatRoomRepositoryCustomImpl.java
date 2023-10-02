package com.wom.ttchat.chatroom.adapter.out.persistence.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wom.ttchat.chatroom.adapter.out.entity.QChatRoomJpaEntity;
import com.wom.ttchat.chatroom.domain.ChatRoomInfo;
import com.wom.ttchat.common.dto.PageRequest;
import com.wom.ttchat.member.domain.Member.MemberId;
import com.wom.ttchat.participant.domain.ParticipantStatus;
import java.util.List;
import java.util.UUID;

import com.wom.ttchat.participant.infrastructure.adapter.out.entity.QParticipantJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.wom.ttchat.participant.infrastructure.adapter.out.entity.QParticipantJpaEntity.participantJpaEntity;
import static com.wom.ttchat.accompany.adapter.out.persistence.QAccompanyJpaEntity.accompanyJpaEntity;
import static com.wom.ttchat.chatroom.adapter.out.entity.QChatRoomJpaEntity.chatRoomJpaEntity;

@Repository
@RequiredArgsConstructor
public class ChatRoomRepositoryCustomImpl implements ChatRoomRepositoryCustom{

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<ChatRoomInfo> findChatRoomListByMemberId(MemberId memberId,
		PageRequest pageRequest) {
		JPAQuery<ChatRoomInfo> query = jpaQueryFactory
			.select(Projections.constructor(
				ChatRoomInfo.class,
				participantJpaEntity.room.uid,
				participantJpaEntity.room.name,
				accompanyJpaEntity.status,
				accompanyJpaEntity.postTitle
			))
			.from(participantJpaEntity)
			.leftJoin(accompanyJpaEntity)
			.on(participantJpaEntity.room.accompanyPostId.eq(accompanyJpaEntity.accompanyPostId))
			.where(participantJpaEntity.member.id.eq(memberId.getValue()),
				participantJpaEntity.status.eq(ParticipantStatus.JOINED.name()))
			.orderBy(participantJpaEntity.room.createdAt.asc());


		if (!pageRequest.isEmpty())
			query = query.offset(pageRequest.getPageNo()).limit(pageRequest.getPageSize());

		return query.fetch();

	}

	@Override
	public boolean isExistDirectRoomByHostAndGuestId(UUID hostId, UUID guestId) {
		Boolean test2 = jpaQueryFactory.select(participantJpaEntity.member.id.count().intValue().gt(0)).from(participantJpaEntity)
				.where(participantJpaEntity.room.hostMemberId.id.eq(hostId),
						participantJpaEntity.member.id.eq(guestId)).fetchOne();
		return  test2;
	}





}
