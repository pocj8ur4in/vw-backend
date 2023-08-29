package vw.core.exception.user;

import vw.core.exception.error.BaseCodeException;
import vw.core.exception.error.UserErrorCode;

public class AlreadyExistNicknameException extends BaseCodeException { // 닉네임이 이미 존재하는 경우
	public static final BaseCodeException baseCodeException = new AlreadyExistNicknameException();

	public AlreadyExistNicknameException() {
		super(UserErrorCode.ALREADY_EXIST_NICKNAME);
	}
}
