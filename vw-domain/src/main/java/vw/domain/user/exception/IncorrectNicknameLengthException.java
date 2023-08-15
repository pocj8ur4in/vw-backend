package vw.domain.user.exception;

import vw.core.exception.error.BaseCodeException;

public class IncorrectNicknameLengthException extends BaseCodeException { // 닉네임 길이가 잘못된 경우
	public static final BaseCodeException baseCodeException =
			new IncorrectNicknameLengthException();

	public IncorrectNicknameLengthException() {
		super(UserErrorCode.INCORRECT_NICKNAME_LENGTH);
	}
}
