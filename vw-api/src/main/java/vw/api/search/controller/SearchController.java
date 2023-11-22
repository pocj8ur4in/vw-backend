package vw.api.search.controller;

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
import vw.api.search.service.SongSearchUseCase;
import vw.core.dto.song.SongSearchRequest;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "SearchController", description = "노래 검색과 관련된 컨트롤러입니다.")
@RequestMapping(value = "v1/search")
public class SearchController {
	private final SongSearchUseCase songSearchUseCase;

	private static final Logger logger =
			LoggerFactory.getLogger(SearchController.class); // SLF4J를 활용한 로그 기록

	@ResponseBody
	@PostMapping("/song")
	@Operation(summary = "노래 검색", description = "노래 검색을 실행합니다.")
	public ResponseEntity<?> searchSongPOST(SongSearchRequest req) { // 노래 검색 실행
		logger.info("> 노래 검색");

		return songSearchUseCase.execute(req);
	}
}
