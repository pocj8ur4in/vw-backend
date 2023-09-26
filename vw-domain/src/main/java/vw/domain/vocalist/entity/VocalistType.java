package vw.domain.vocalist.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum VocalistType { // 가수 유형
	VOCALOID("VOCALOID"),
	VOICEROID("VOICEROID"),
	UTAU("UTAU"),
	CEVIO("CEVIO"),
	SYNTHESIZER_V("SYNTHESIZER_V"),
	NEUTRINO("NEUTRINO"),
	VOISONA("VOISONA"),
	NEW_TYPE("NEW_TYPE"),
	VOCALINA("VOCALINA"),
	OTHER("OTHER");

	private final String value; // 가수 유형 값
}
