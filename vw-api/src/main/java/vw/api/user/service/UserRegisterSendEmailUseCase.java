package vw.api.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import vw.core.annotation.UseCase;
import vw.core.dto.user.RegisterSendEmailRequest;
import vw.core.exception.error.BaseCodeException;
import vw.domain.common.handler.EmailHandler;
import vw.domain.user.service.UserService;

@UseCase
@RequiredArgsConstructor
public class UserRegisterSendEmailUseCase { // 이메일 인증 전송
	private final UserService userService;
	private final EmailHandler emailHandler;

	@Transactional
	public ResponseEntity<String> execute(RegisterSendEmailRequest req) {
		try {
			// 이메일 유효성 검사
			userService.chkEmptyEmail(req.getEmail());
			userService.chkPatternEmail(req.getEmail());
			userService.chkAlreadyExistEmail(req.getEmail());
			emailHandler.chkAlreadySendEmail(req.getEmail());

			// 인증 이메일 전송
			emailHandler.sendAuthEmail(req.getEmail());

			return ResponseEntity.ok().body("해당 이메일로 인증 메시지가 전달되었습니다. (유효시간 : 5분)");

		} catch (BaseCodeException baseCodeException) {
			return ResponseEntity.status(baseCodeException.getErrorReason().getStatus())
					.body(baseCodeException.getErrorReason().getReason());
		}
	}
}
