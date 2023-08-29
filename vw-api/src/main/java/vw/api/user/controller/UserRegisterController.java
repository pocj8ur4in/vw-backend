package vw.api.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vw.api.user.service.*;
import vw.core.dto.user.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "UserRegisterController", description = "회원가입과 관련된 컨트롤러입니다.")
@RequestMapping(value = "v1/user")
public class UserRegisterController {
	private final UserRegisterChkIdUseCase userRegisterChkIdUseCase;
	private final UserRegisterChkNicknameUseCase userRegisterChkNicknameUseCase;
	private final UserRegisterChkEmailUseCase userRegisterChkEmailUseCase;
	private final UserRegisterSendEmailUseCase userRegisterSendEmailUseCase;
	private final UserRegisterAuthEmailUseCase userRegisterAuthEmailUseCase;
	private final UserRegisterUseCase userRegisterUseCase;

	private static final Logger logger =
			LoggerFactory.getLogger(UserRegisterController.class); // SLF4J를 활용한 로그 기록

	@ResponseBody
	@PostMapping("/register/chk/id")
	@Operation(summary = "아이디 유효성 검사", description = "아이디 유효성 검사를 검사합니다.")
	public ResponseEntity<String> userRegisterChkIdPOST(RegisterChkIdRequest req) {
		logger.info("> 회원가입 아이디 유효성 검사");

		return userRegisterChkIdUseCase.execute(req);
	}

	@ResponseBody
	@PostMapping("/register/chk/nickname")
	@Operation(summary = "닉네임 유효성 검사", description = "닉네임 유효성 검사를 검사합니다.")
	public ResponseEntity<String> userRegisterChkNicknamePOST(RegisterChkNicknameRequest req) {
		logger.info("> 회원가입 닉네임 유효성 검사");
		return userRegisterChkNicknameUseCase.execute(req);
	}

	@ResponseBody
	@PostMapping("/register/chk/email")
	@Operation(summary = "이메일 유효성 검사", description = "이메일 유효성 검사를 실행합니다.")
	public ResponseEntity<String> userRegisterChkEmailPOST(RegisterChkEmailRequest req) {
		logger.info("> 회원가입 이메일 유효성 검사");

		return userRegisterChkEmailUseCase.execute(req);
	}

	@ResponseBody
	@PostMapping("/register/send/email")
	@Operation(summary = "이메일 인증 전송", description = "이메일 인증 전송을 실행합니다.")
	public ResponseEntity<String> userRegisterSendEmailPOST(RegisterSendEmailRequest req) {
		logger.info("> 회원가입 이메일 인증 전송");

		return userRegisterSendEmailUseCase.execute(req);
	}

	@ResponseBody
	@PostMapping("/register/auth/email")
	@Operation(summary = "이메일 인증 확인", description = "이메일 인증 확인을 실행합니다.")
	public ResponseEntity<String> userRegisterAuthEmailPOST(
			@RequestParam("email") String email, @RequestParam("key") String authKey) {
		logger.info("> 회원가입 이메일 인증 확인");

		return userRegisterAuthEmailUseCase.execute(
				RegisterAuthEmailRequest.builder().email(email).authKey(authKey).build());
	}

	@ResponseBody
	@PostMapping("/register")
	@Operation(summary = "회원가입", description = "회원가입을 실행합니다.")
	public ResponseEntity<String> userRegisterPOST(RegisterRequest req) {
		logger.info("> 회원가입");

		return userRegisterUseCase.execute(req);
	}
}
