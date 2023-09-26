package vw.domain.vocalist.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "tbl_vocalist_post")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VocalistPost {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vocalist_post_index", nullable = false, unique = true)
	private long index; // 가수 포스트 식별자

	@Lob
	@Column(name = "vocalist_post_content")
	private String content; // 가수 포스트 내용

	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Vocalist vocalist; // 가수 식별자
}
