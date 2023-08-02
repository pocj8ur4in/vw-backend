package vw.api.search.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vw.api.user.controller.UserController;

@Controller
@RequiredArgsConstructor
@Slf4j
@Tag(name = "2. search")
@RequestMapping(value = "/search", method = RequestMethod.GET)
public class SearchController {
    private static final Logger logger =
            LoggerFactory.getLogger(UserController.class); // SLF4J를 활용한 로그 기록

    // 가수 검색 이동
    @RequestMapping(value = "/vocalist", method = RequestMethod.GET)
    public String vocalistGET() {
        logger.info("가수 검색 이동");
        return "search/vocalist";
    }

    // 아티스트 검색 이동
    @RequestMapping(value = "/artist", method = RequestMethod.GET)
    public String artistGET() {
        logger.info("아티스트 검색 이동");
        return "search/artist";
    }

    // 노래 검색 이동
    @RequestMapping(value = "/song", method = RequestMethod.GET)
    public String songGET() {
        logger.info("노래 검색 이동");
        return "search/song";
    }
}
