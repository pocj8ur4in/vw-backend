package vw.api.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import vw.core.annotation.UseCase;
import vw.core.dto.user.LoginRequest;
import vw.core.dto.user.LoginResponse;
import vw.core.exception.error.BaseCodeException;
import vw.domain.common.handler.JwtHandler;
import vw.domain.user.service.UserService;

@UseCase
@RequiredArgsConstructor
public class UserLoginUseCase { // 일반 로그인 실행
	private final UserService userService;
	private final JwtHandler jwtHandler;

	@Transactional
	public ResponseEntity<?> execute(LoginRequest req) {
		try {
			Long index = userService.login(req.getId(), req.getPassword());
			LoginResponse loginResponse =
					LoginResponse.builder()
							.accessToken(jwtHandler.generateAccessToken(index))
							.refreshToken(jwtHandler.generateRefreshToken(index))
							.build();

			return ResponseEntity.ok(loginResponse);
		} catch (BaseCodeException baseCodeException) {
			return ResponseEntity.status(baseCodeException.getErrorReason().getStatus())
					.body(baseCodeException.getErrorReason().getReason());
		}
	}
}
