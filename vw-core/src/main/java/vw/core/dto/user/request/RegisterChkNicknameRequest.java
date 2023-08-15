package vw.core.dto.user.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegisterChkNicknameRequest {
	private String nickname; // 회원 닉네임
}
