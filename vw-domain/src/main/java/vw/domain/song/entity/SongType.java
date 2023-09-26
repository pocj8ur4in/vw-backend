package vw.domain.song.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SongType { // 노래 유형
	ORIGINAL("ORIGINAL"),
	COVER("COVER"),
	REMASTER("REMASTER"),
	MASHUP("MASHUP"),
	REMIX("REMIX"),
	MUSIC_PV("MUSIC_PV");

	private final String value; // 노래 유형 값
}
