package vw.domain.common.model;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@MappedSuperclass // JPA Entity 클래스들이 BaseTimeEntity를 상속할 때 내부 필드도 컬럼으로 인식하도록 설정
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseDateTime { // 생성 및 수정 시간 엔티티
	@Column(name = "baseDateTime_created", updatable = false)
	@CreatedDate
	private LocalDateTime created; // 생성 시간

	@Column(name = "baseDateTime_lastModified")
	@LastModifiedDate
	private LocalDateTime lastModified; // 수정 시간
}
