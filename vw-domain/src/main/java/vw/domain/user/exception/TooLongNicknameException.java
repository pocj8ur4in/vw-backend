package vw.domain.user.exception;

import vw.core.exception.error.BaseCodeException;

public class TooLongNicknameException extends BaseCodeException { // 닉네임 길이가 너무 긴 경우
    public static final BaseCodeException baseCodeException = new TooLongNicknameException();

    public TooLongNicknameException() {
        super(UserErrorCode.TOO_LONG_NICKNAME);
    }
}
