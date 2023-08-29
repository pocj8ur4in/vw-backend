package vw.core.exception.user;

import vw.core.exception.error.BaseCodeException;
import vw.core.exception.error.UserErrorCode;

public class EmptyNicknameException extends BaseCodeException { // 닉네임이 공란인 경우
	public static final BaseCodeException baseCodeException = new EmptyNicknameException();

	public EmptyNicknameException() {
		super(UserErrorCode.EMPTY_NICKNAME);
	}
}
