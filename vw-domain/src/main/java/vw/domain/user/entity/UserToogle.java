package vw.domain.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserToogle { // 회원 토글
	@Column(name = "user_receive_email", nullable = false)
	private Boolean receiveEmail = Boolean.TRUE; // 회원 이메일 수신 여부

	@Builder
	public UserToogle(Boolean receiveEmail) { // Builder 패턴으로 UserToogle 객체를 생성 가능하게 함
		this.receiveEmail = receiveEmail;
	}

	public void setReceiveEmail(Boolean receiveEmail) { // 이메일 수신 여부 재설정
		this.receiveEmail = receiveEmail;
	}
}
