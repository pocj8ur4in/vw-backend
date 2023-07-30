package vw.domain.user.exception;

import vw.core.exception.error.BaseCodeException;

public class TooLongIdException extends BaseCodeException { // 아이디 길이가 너무 긴 경우
    public static final BaseCodeException baseCodeException = new TooLongIdException();

    public TooLongIdException() {
        super(UserErrorCode.TOO_LONG_ID);
    }
}
