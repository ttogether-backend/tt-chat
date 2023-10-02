package com.wom.ttchat.member.adapter.out.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "member")
@Builder
public class MemberJpaEntity {
	@Id
	private UUID id;
	@Column
	private String nickname;
	@Column
	private String profileImagePath;
	@Column
	private String bio;
	@Column(length = 10)
	private String certificationStatus;
	@Column
	private String accountStatus;

}
