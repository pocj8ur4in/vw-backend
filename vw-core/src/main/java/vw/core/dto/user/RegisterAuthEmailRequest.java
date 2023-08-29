package vw.core.dto.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegisterAuthEmailRequest {
	private String email; // 회원 이메일
	private String authKey; // 인증 키
}
