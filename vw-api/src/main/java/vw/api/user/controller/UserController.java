package vw.api.user.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequiredArgsConstructor
@Slf4j
@Tag(name = "1. user")
@RequestMapping(value = "/user", method = RequestMethod.GET)
public class UserController {
    private static final Logger logger =
            LoggerFactory.getLogger(UserController.class); // SLF4J를 활용한 로그 기록

    // 로그인 페이지 이동
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGET() {
        logger.info("로그인 페이지 이동");
        return "user/login";
    }

    // 회원가입 페이지 이동
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerGET() {
        logger.info("회원가입 페이지 이동");
        return "user/register";
    }
}
