package vw.core.exception.user;

import vw.core.exception.error.BaseCodeException;
import vw.core.exception.error.UserErrorCode;

public class IncorrectNicknameLengthException extends BaseCodeException { // 닉네임 길이가 잘못된 경우
	public static final BaseCodeException baseCodeException =
			new IncorrectNicknameLengthException();

	private IncorrectNicknameLengthException() {
		super(UserErrorCode.INCORRECT_NICKNAME_LENGTH);
	}
}
