package vw.core.dto.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegisterChkEmailRequest {
	private String email; // 회원 이메일
}
