package vw.domain.vocalist.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import vw.domain.common.entity.LinkType;

@Entity
@Table(name = "tbl_vocalist_link")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VocalistLink {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vocalist_link_index", nullable = false, unique = true)
	private long index; // 가수 링크 식별자

	@Enumerated(EnumType.STRING)
	@Column(name = "vocalist_link_type", nullable = false)
	private LinkType type; // 가수 링크 유형

	@Column(name = "vocalist_link_description")
	private String description; // 가수 링크 설명

	@Lob
	@Column(name = "vocalist_link_content", nullable = false)
	private String content; // 가수 링크 내용

	@Column(name = "vocalist_link_is_official", nullable = false)
	private Boolean isOfficial; // 가수 링크 공식 여부

	@Column(name = "vocalist_link_is_deleted", nullable = false)
	private Boolean isDeleted; // 가수 링크 삭제 여부

	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Vocalist vocalist; // 가수 식별자
}
