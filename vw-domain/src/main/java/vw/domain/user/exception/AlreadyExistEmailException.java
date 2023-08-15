package vw.domain.user.exception;

import vw.core.exception.error.BaseCodeException;

public class AlreadyExistEmailException extends BaseCodeException { // 이메일이 이미 존재하는 경우
	public static final BaseCodeException baseCodeException = new AlreadyExistEmailException();

	public AlreadyExistEmailException() {
		super(UserErrorCode.ALREADY_EXIST_EMAIL);
	}
}
