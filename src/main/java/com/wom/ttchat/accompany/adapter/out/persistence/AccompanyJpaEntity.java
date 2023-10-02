package com.wom.ttchat.accompany.adapter.out.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "accompany")
public class AccompanyJpaEntity {
	@Id
	private Long accompanyId;
	@Column
	private Long accompanyPostId;
	@Column
	private String status;
	@Column
	private String postTitle;
	@Column
	private UUID hostId;

}
