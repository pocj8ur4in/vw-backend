package vw.api.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import vw.core.annotation.UseCase;
import vw.core.dto.user.RegisterAuthEmailRequest;
import vw.core.exception.error.BaseCodeException;
import vw.domain.common.handler.EmailHandler;

@UseCase
@RequiredArgsConstructor
public class UserRegisterAuthEmailUseCase { // 이메일 인증 확인
	private final EmailHandler emailHandler;

	@Transactional
	public ResponseEntity<String> execute(RegisterAuthEmailRequest req) {
		try {
			emailHandler.authAuthKey(req.getEmail(), req.getAuthKey());

			return ResponseEntity.ok().body("이메일이 인증되었습니다.");
		} catch (BaseCodeException baseCodeException) {
			return ResponseEntity.status(baseCodeException.getErrorReason().getStatus())
					.body(baseCodeException.getErrorReason().getReason());
		}
	}
}
