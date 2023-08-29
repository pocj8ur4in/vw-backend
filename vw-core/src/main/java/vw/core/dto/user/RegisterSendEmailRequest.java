package vw.core.dto.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegisterSendEmailRequest {
	private String email; // 회원 이메일
}
