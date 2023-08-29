package vw.api.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import vw.core.annotation.UseCase;
import vw.core.dto.user.RegisterChkIdRequest;
import vw.core.exception.error.BaseCodeException;
import vw.domain.user.service.UserService;

@UseCase
@RequiredArgsConstructor
public class UserRegisterChkIdUseCase {
	private final UserService userService;

	@Transactional
	public ResponseEntity<String> execute(RegisterChkIdRequest req) {
		try {
			userService.chkEmptyId(req.getId());
			userService.chkLengthId(req.getId());
			userService.chkPatternId(req.getId());
			userService.chkAlreadyExistId(req.getId());

			return ResponseEntity.ok().body("사용 가능한 아이디입니다.");

		} catch (BaseCodeException baseCodeException) {
			return ResponseEntity.status(baseCodeException.getErrorReason().getStatus())
					.body(baseCodeException.getErrorReason().getReason());
		}
	}
}
