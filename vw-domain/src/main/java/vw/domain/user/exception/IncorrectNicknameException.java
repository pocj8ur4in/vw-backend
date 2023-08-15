package vw.domain.user.exception;

import vw.core.exception.error.BaseCodeException;

public class IncorrectNicknameException extends BaseCodeException { // 닉네임 형식이 잘못된 경우
	public static final BaseCodeException baseCodeException = new IncorrectNicknameException();

	public IncorrectNicknameException() {
		super(UserErrorCode.INCORRECT_NICKNAME);
	}
}
