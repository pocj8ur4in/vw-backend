package vw.api.view.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import vw.api.user.controller.UserRegisterController;

@Controller
@RequiredArgsConstructor
@Slf4j
@Tag(name = "ViewController", description = "화면 이동과 관련된 컨트롤러입니다.")
public class ViewController {
	private static final Logger logger =
			LoggerFactory.getLogger(UserRegisterController.class); // SLF4J를 활용한 로그 기록

	@GetMapping(value = "/")
	@Operation(summary = "홈 화면 이동", description = "홈 화면으로 이동합니다.")
	public String homeGET() {
		logger.info("홈 화면 이동");

		return "home";
	}

	@GetMapping(value = "/user/register")
	@Operation(summary = "회원가입 화면 이동", description = "회원가입 화면으로 이동합니다.")
	public String userRegisterGET() {
		logger.info("회원가입 화면 이동");

		return "user/register";
	}

	@GetMapping(value = "/user/login")
	@Operation(summary = "로그인 화면 이동", description = "로그인 화면으로 이동합니다.")
	public String userLoginGET() {
		logger.info("로그인 페이지 이동");

		return "user/login";
	}

	@GetMapping(value = "/post/vocalist")
	@Operation(summary = "가수 포스트 화면 이동", description = "가수 포스트 화면으로 이동합니다.")
	public String postVocalistGET() {
		logger.info("가수 포스트 화면 이동");

		return "post/vocalist";
	}

	@GetMapping(value = "/post/artist")
	@Operation(summary = "아티스트 포스트 화면 이동", description = "아티스트 포스트 화면으로 이동합니다.")
	public String postArtistGET() {
		logger.info("아티스트 포스트 화면 이동");

		return "post/artist";
	}

	@GetMapping(value = "/post/song")
	@Operation(summary = "노래 포스트 화면 이동", description = "노래 포스트 화면으로 이동합니다.")
	public String postSongGET() {
		logger.info("노래 포스트 이동");

		return "post/song";
	}

	@GetMapping(value = "search/vocalist")
	@Operation(summary = "가수 검색 화면 이동", description = "가수 검색 화면으로 이동합니다.")
	public String searchVocalistGET() {
		logger.info("가수 검색 이동");

		return "search/vocalist";
	}

	@GetMapping(value = "search/artist")
	@Operation(summary = "아티스트 검색 화면 이동", description = "아티스트 검색 화면으로 이동합니다.")
	public String searchArtistGET() {
		logger.info("아티스트 검색 이동");

		return "search/artist";
	}

	@GetMapping(value = "search/song")
	@Operation(summary = "노래 검색 화면 이동", description = "노래 검색 화면으로 이동합니다.")
	public String searchSongGET() {
		logger.info("노래 검색 이동");

		return "search/song";
	}
}
