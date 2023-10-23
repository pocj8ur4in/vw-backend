package vw.domain.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LinkCategory { // 링크 카테고리
	MEDIA("MEDIA"),
	REFERENCE("REFERENCE");

	private final String value; // 링크 카테고리 값
}
