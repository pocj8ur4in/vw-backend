package vw.domain.user.exception;

import vw.core.exception.error.BaseCodeException;

public class DifferentPasswordException extends BaseCodeException { // 비밀번호와 비밀번호 확인이 일치하지 않는 경우
	public static final BaseCodeException baseCodeException = new DifferentPasswordException();

	public DifferentPasswordException() {
		super(UserErrorCode.DIFFERENT_PASSWORD);
	}
}
