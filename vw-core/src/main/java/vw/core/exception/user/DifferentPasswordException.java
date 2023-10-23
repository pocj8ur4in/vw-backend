package vw.core.exception.user;

import vw.core.exception.error.BaseCodeException;
import vw.core.exception.error.UserErrorCode;

public class DifferentPasswordException extends BaseCodeException { // 비밀번호와 비밀번호 확인이 일치하지 않는 경우
	public static final BaseCodeException baseCodeException = new DifferentPasswordException();

	private DifferentPasswordException() {
		super(UserErrorCode.DIFFERENT_PASSWORD);
	}
}
