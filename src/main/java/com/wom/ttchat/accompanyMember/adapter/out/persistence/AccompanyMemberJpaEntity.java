package com.wom.ttchat.accompanyMember.adapter.out.persistence;

import com.wom.ttchat.accompany.adapter.out.persistence.AccompanyJpaEntity;
import com.wom.ttchat.common.BaseTimeEntity;
import com.wom.ttchat.member.adapter.out.persistence.MemberJpaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "accompany_member")
public class AccompanyMemberJpaEntity extends BaseTimeEntity {

  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne
  @JoinColumn(name = "accompany_id")
  private AccompanyJpaEntity accompany;

  @ManyToOne
  @JoinColumn(name = "member_id")
  private MemberJpaEntity member;

  @Column(length = 10)
  private String status;

  @Column(length = 10)
  private String role;
}
