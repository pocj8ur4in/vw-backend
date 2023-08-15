package vw.domain.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import vw.domain.common.vo.Image;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserProfile { // 회원 프로필
	@Column(name = "user_nickname", unique = true, length = 12, nullable = false)
	private String nickname; // 회원 닉네임

	@Column(name = "user_email", unique = true, nullable = false)
	private String email; // 회원 이메일

	@Embedded
	@Column(name = "image_key")
	private Image image; // 회원 이미지

	@Builder
	public UserProfile(
			String nickname, String email, String image) { // Builder 패턴으로 UserProfile 객체를 생성 가능하게 함
		this.nickname = nickname;
		this.email = email;
		this.image = Image.valueOf(image);
	}
}
