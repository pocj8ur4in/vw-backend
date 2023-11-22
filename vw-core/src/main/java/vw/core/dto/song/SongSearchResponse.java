package vw.core.dto.song;

import java.util.Date;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SongSearchResponse {
	private String thumbnailUrl; // 노래 썸네일 주소
	private String name; // 노래 이름
	private String type; // 노래 타입
	private List<String> artist; // 노래 아티스트
	private List<String> vocalist; // 노래 가수
	private Date releaseDate; // 노래 발매일
}
