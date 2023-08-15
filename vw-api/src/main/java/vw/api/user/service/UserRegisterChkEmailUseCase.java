package vw.api.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import vw.core.annotation.UseCase;
import vw.core.dto.user.request.RegisterChkEmailRequest;
import vw.core.exception.error.BaseCodeException;
import vw.domain.user.service.UserService;

@UseCase
@RequiredArgsConstructor
public class UserRegisterChkEmailUseCase {
	private final UserService userService;

	@Transactional
	public ResponseEntity<String> execute(RegisterChkEmailRequest req) {
		try {
			userService.chkEmptyEmail(req.getEmail());
			userService.chkPatternEmail(req.getEmail());
			userService.chkAlreadyExistEmail(req.getEmail());

			return ResponseEntity.ok().body("사용 가능한 이메일입니다.");

		} catch (BaseCodeException baseCodeException) {
			return ResponseEntity.status(baseCodeException.getErrorReason().getStatus())
					.body(baseCodeException.getErrorReason().getReason());
		}
	}
}
