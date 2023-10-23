package vw.core.exception.user;

import vw.core.exception.error.BaseCodeException;
import vw.core.exception.error.UserErrorCode;

public class IncorrectNicknameException extends BaseCodeException { // 닉네임 형식이 잘못된 경우
	public static final BaseCodeException baseCodeException = new IncorrectNicknameException();

	private IncorrectNicknameException() {
		super(UserErrorCode.INCORRECT_NICKNAME);
	}
}
