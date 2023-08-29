package vw.core.exception.user;

import vw.core.exception.error.BaseCodeException;
import vw.core.exception.error.UserErrorCode;

public class AlreadyExistIdException extends BaseCodeException { // 아이디가 이미 존재하는 경우
	public static final BaseCodeException baseCodeException = new AlreadyExistIdException();

	public AlreadyExistIdException() {
		super(UserErrorCode.ALREADY_EXIST_ID);
	}
}
