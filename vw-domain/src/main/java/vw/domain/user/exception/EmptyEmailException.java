package vw.domain.user.exception;

import vw.core.exception.error.BaseCodeException;

public class EmptyEmailException extends BaseCodeException { // 이메일이 공란인 경우
	public static final BaseCodeException baseCodeException = new EmptyEmailException();

	public EmptyEmailException() {
		super(UserErrorCode.EMPTY_EMAIL);
	}
}
