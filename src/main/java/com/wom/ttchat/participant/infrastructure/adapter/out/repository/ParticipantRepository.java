package com.wom.ttchat.participant.infrastructure.adapter.out.repository;

import com.wom.ttchat.participant.infrastructure.adapter.out.entity.ParticipantJpaEntity;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ParticipantRepository extends JpaRepository<ParticipantJpaEntity, Long> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE participants SET status = :status WHERE room_id = :roomId AND member_id = :memberId", nativeQuery = true)
    void updateStatusByRoomIdAndMemberId(@Param("status") String status, @Param("roomId") Long roomId, @Param("memberId") UUID memberId);

    List<ParticipantJpaEntity> findAllByRoomIdAndStatusEquals(Long chatRoomId, String status);
    ParticipantJpaEntity findByRoomIdAndMemberId(Long roomId, UUID memberId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE participants p SET p.read_at = :readAt WHERE p.room_id = :roomId AND p.member_id = :memberId", nativeQuery = true)
    void updateReadyAtByRoomIdAndMemberId(@Param("readAt") LocalDateTime readAt, @Param("roomId") Long roomId, @Param("memberId") UUID memberId);

    Optional<ParticipantJpaEntity> findByMemberIdAndRoomId(UUID memberId, Long roomId);

    Optional<ParticipantJpaEntity> findByRoomIdAndMemberIdAndStatusEquals(Long roomId, UUID memberId, String status);
}
