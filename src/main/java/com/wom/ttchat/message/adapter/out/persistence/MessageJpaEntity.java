package com.wom.ttchat.message.adapter.out.persistence;

import com.wom.ttchat.message.domain.MessageType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Builder
@Document(collection = "message")
public class MessageJpaEntity {

	@Id
	@Field(value = "_id", targetType = FieldType.OBJECT_ID)
	private String id;
	private String roomUId;
	private String memberId;
	private String nickname;
	private String content;
	private MessageType type;
	private LocalDateTime createAt;


}
