package vw.api.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import vw.core.annotation.UseCase;
import vw.core.dto.user.RegisterRequest;
import vw.core.exception.error.BaseCodeException;
import vw.domain.common.handler.EmailHandler;
import vw.domain.user.service.UserService;

@UseCase
@RequiredArgsConstructor
public class UserRegisterUseCase { // 회원가입 실행
	private final UserService userService;
	private final EmailHandler emailHandler;

	@Transactional
	public ResponseEntity<String> execute(RegisterRequest req) {
		try {
			// 아이디 유효성 검사
			userService.chkEmptyId(req.getId());
			userService.chkLengthId(req.getId());
			userService.chkPatternId(req.getId());
			userService.chkAlreadyExistId(req.getId());

			// 닉네임 유효성 검사
			userService.chkEmptyNickname(req.getNickname());
			userService.chkLengthNickname(req.getNickname());
			userService.chkPatterNickname(req.getNickname());
			userService.chkAlreadyExistNickname(req.getNickname());

			// 이메일 유효성 검사
			userService.chkEmptyEmail(req.getEmail());
			userService.chkPatternEmail(req.getEmail());
			userService.chkAlreadyExistEmail(req.getEmail());

			// 이메일 인증 활성화 여부 확인
			emailHandler.chkAuth(req.getEmail());

			// 회원가입 실행
			userService.register(
					req.getId(),
					req.getPassword1(),
					req.getNickname(),
					req.getEmail(),
					req.getReceiveEmail());

			// 이메일 인증 삭제
			emailHandler.deleteAuth(req.getEmail());

			return ResponseEntity.ok().body("회원가입이 성공하였습니다.");

		} catch (BaseCodeException baseCodeException) {
			return ResponseEntity.status(baseCodeException.getErrorReason().getStatus())
					.body(baseCodeException.getErrorReason().getReason());
		}
	}
}
