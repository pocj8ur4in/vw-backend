package vw.api.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import vw.core.annotation.UseCase;
import vw.core.dto.user.RegisterChkNicknameRequest;
import vw.core.exception.error.BaseCodeException;
import vw.domain.user.service.UserService;

@UseCase
@RequiredArgsConstructor
public class UserRegisterChkNicknameUseCase { // 닉네임 유효성 검사
	private final UserService userService;

	@Transactional
	public ResponseEntity<String> execute(RegisterChkNicknameRequest req) {
		try {
			userService.chkEmptyNickname(req.getNickname());
			userService.chkLengthNickname(req.getNickname());
			userService.chkPatterNickname(req.getNickname());
			userService.chkAlreadyExistNickname(req.getNickname());

			return ResponseEntity.ok().body("사용 가능한 닉네임입니다.");

		} catch (BaseCodeException baseCodeException) {
			return ResponseEntity.status(baseCodeException.getErrorReason().getStatus())
					.body(baseCodeException.getErrorReason().getReason());
		}
	}
}
