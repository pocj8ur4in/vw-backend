package vw.core.dto.error;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class BaseErrorResponse { // 오류에 대한 응답을 나타내는 DTO
	private final boolean success = false;

	private final Integer status; // HTTP 상태 코드
	private final String code; // HTTP 상태 코드 (문자열)
	private final String reason; // 오류의 원인
	private final LocalDateTime timeStamp; // 오류가 발생한 시간
	private final String path; // 오류가 발생한 경로

	public BaseErrorResponse(BaseErrorReason baseErrorReason, String path) {
		this.status = baseErrorReason.getStatus();
		this.code = baseErrorReason.getCode();
		this.reason = baseErrorReason.getReason();
		this.timeStamp = LocalDateTime.now();
		this.path = path;
	}

	public BaseErrorResponse(int status, String code, String reason, String path) {
		this.status = status;
		this.code = code;
		this.reason = reason;
		this.timeStamp = LocalDateTime.now();
		this.path = path;
	}
}
