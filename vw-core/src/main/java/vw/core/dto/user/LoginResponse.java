package vw.core.dto.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {
	private String accessToken; // accessToken
	private String refreshToken; // refreshToken
}
