package vw.domain.song.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SongLyricsLanguage { // 노래 가사 언어
	Japanese("Japanese"),
	English("English"),
	Korean("Korean"),
	Chinese("Chinese");

	private final String value; // 노래 가사 언어 값
}
