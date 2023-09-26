package vw.core.dto.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginRequest {
	private String id; // 회원 아이디
	private String password; // 회원 비밀번호
}
