package vw.core.exception.jwt;

import vw.core.exception.BaseCodeException;
import vw.core.exception.GlobalErrorCode;

public class InvalidTokenException extends BaseCodeException { // // 토큰이 유효하지 않은 경우
    public static final BaseCodeException baseCodeException = new InvalidTokenException();

    private InvalidTokenException() {
        super(GlobalErrorCode.TOKEN_INVALID);
    }
}
