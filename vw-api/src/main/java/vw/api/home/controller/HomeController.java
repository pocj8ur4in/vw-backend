package vw.api.home.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import vw.api.user.controller.UserController;

@Controller
@RequiredArgsConstructor
@Slf4j
@Tag(name = "0. home")
public class HomeController {
    private static final Logger logger =
            LoggerFactory.getLogger(UserController.class); // SLF4J를 활용한 로그 기록

    // 홈 페이지 이동
    @GetMapping(value = "/")
    public String homeGET() {
        logger.info("홈 페이지 이동");
        return "home";
    }
}
