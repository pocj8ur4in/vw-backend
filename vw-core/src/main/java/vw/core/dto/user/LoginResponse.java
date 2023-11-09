package vw.core.dto.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {
	private String accessToken; // 액세스 토큰
	private String refreshToken; // 리프래쉬 토큰
}
