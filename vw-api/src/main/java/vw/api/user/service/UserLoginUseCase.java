package vw.api.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import vw.core.annotation.UseCase;
import vw.core.dto.user.LoginRequest;
import vw.core.exception.error.BaseCodeException;
import vw.domain.user.service.UserService;

@UseCase
@RequiredArgsConstructor
public class UserLoginUseCase {
	private final UserService userService;

	@Transactional
	public ResponseEntity<String> execute(LoginRequest req) {
		try {
			// LoginResponse loginResponse = userService.login(req.getId(), req.getPassword());

			return ResponseEntity.ok().body("로그인이 성공하였습니다.");
		} catch (BaseCodeException baseCodeException) {
			return ResponseEntity.status(baseCodeException.getErrorReason().getStatus())
					.body(baseCodeException.getErrorReason().getReason());
		}
	}
}
