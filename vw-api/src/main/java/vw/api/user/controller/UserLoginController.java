package vw.api.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import vw.api.user.service.UserLoginUseCase;
import vw.core.dto.user.LoginRequest;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "UserLoginController", description = "로그인과 관련된 컨트롤러입니다.")
@RequestMapping(value = "v1/user")
public class UserLoginController {
	private final UserLoginUseCase userLoginUseCase;

	private static final Logger logger =
			LoggerFactory.getLogger(UserLoginController.class); // SLF4J를 활용한 로그 기록

	@ResponseBody
	@PostMapping("/login")
	@Operation(summary = "로그인", description = "로그인을 실행합니다.")
	public ResponseEntity<String> userLoginPOST(LoginRequest req) {
		logger.info("> 로그인");

		return userLoginUseCase.execute(req);
	}
}
