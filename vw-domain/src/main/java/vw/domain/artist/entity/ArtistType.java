package vw.domain.artist.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ArtistType { // 아티스트 유형
	PRODUCER("PRODUCER"),
	VOCALIST("VOCALIST"),
	CHORUS("CHORUS"),
	ANIMATOR("ANIMATOR"),
	ILLUSTRATOR("ILLUSTRATOR"),
	INSTRUMENTALIST("INSTRUMENTALIST"),
	LYRICIST("LYRICIST"),
	COMPOSER("COMPOSER"),
	ARRANGER("ARRANGER"),
	VOCAL_DATA_PROVIDER("VOCAL_DATA_PROVIDER"),
	MASTERING("MASTERING"),
	MIXING("MIXING"),
	VOICE_MANIPULATOR("VOICE_MANIPULATOR"),
	PUBLISHER("PUBLISHER"),
	OTHER("OTHER");

	private final String value; // 아티스트 유형 값
}
