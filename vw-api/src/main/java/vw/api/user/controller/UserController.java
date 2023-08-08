package vw.api.user.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vw.core.dto.user.LoginRequest;
import vw.core.dto.user.LoginResponse;
import vw.core.dto.user.RegisterRequest;
import vw.domain.user.service.UserService;

@Controller
@RequiredArgsConstructor
@Slf4j
@Tag(name = "1. user")
@RequestMapping(value = "/user")
public class UserController {
    private final UserService userService;

    private static final Logger logger =
            LoggerFactory.getLogger(UserController.class); // SLF4J를 활용한 로그 기록

    // 로그인 페이지 이동
    @GetMapping(value = "/login")
    public String userLoginGET() {
        logger.info("로그인 페이지 이동");

        return "user/login";
    }

    // 로그인 실행
    @PostMapping("/login")
    public LoginResponse userRegisterPOST(LoginRequest req) {
        logger.info("로그인 실행");

        return userService.login(req);
    }

    // 회원가입 페이지 이동
    @GetMapping(value = "/register")
    public String userRegisterGET() {
        logger.info("회원가입 페이지 이동");

        return "user/register";
    }

    // 회원가입 실행
    @PostMapping("/register")
    public String userRegisterPOST(RegisterRequest req) {
        logger.info("회원가입 실행");

        userService.register(req);

        return "redirect:/";
    }
}
