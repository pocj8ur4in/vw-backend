package vw.core.dto.error;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BaseErrorReason { // 오류의 원인을 나타내는 DTO
	private final Integer status; // HTTP 상태 코드
	private final String code; // HTTP 상태 코드 (문자열)
	private final String reason; // 오류의 원인
}
