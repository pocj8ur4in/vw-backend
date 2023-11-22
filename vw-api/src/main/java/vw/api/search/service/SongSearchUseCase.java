package vw.api.search.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import vw.core.annotation.UseCase;
import vw.core.dto.song.SongSearchRequest;
import vw.core.dto.song.SongSearchResponse;
import vw.core.exception.error.BaseCodeException;
import vw.domain.song.service.SongService;

@UseCase
@RequiredArgsConstructor
public class SongSearchUseCase { // 노래 검색
	private final SongService songSearchService;

	@Transactional
	public ResponseEntity<?> execute(SongSearchRequest req) {
		try {
			String songSearchQuery =
					songSearchService.createSongSearchQuery(
							req.getName(),
							req.getType(),
							req.getArtist(),
							req.getVocalist(),
							req.getParent(),
							req.getSort(),
							req.getNum(),
							req.getYear(),
							req.getMonth(),
							req.getDay()); // 노래 검색 쿼리 생성

			return ResponseEntity.ok(SongSearchResponse.builder().build());
		} catch (BaseCodeException baseCodeException) {
			return ResponseEntity.status(baseCodeException.getErrorReason().getStatus())
					.body(baseCodeException.getErrorReason().getReason());
		}
	}
}
