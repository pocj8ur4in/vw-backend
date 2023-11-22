package vw.core.dto.song;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SongSearchRequest {
	private String name; // 노래 이름
	private String type; // 노래 타입
	private String artist; // 노래 아티스트
	private String vocalist; // 노래 가수
	private String parent; // 노래 상위 항목
	private String sort; // 노래 정렬
	private String num; // 노래 개수
	private String year; // 노래 발매연도
	private String month; // 노래 발매월
	private String day; // 노래 발매일
}
