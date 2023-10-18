package vw.domain.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "tbl_user_post")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserPost { // 회원 포스트 엔티티
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_post_index", nullable = false, unique = true)
	private long index; // 회원 포스트 식별자

	@Lob
	@Column(name = "user_post_content")
	private String content; // 회원 포스트 내용

	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user; // 회원 식별자
}
