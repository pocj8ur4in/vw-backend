package vw.core.exception.error;

import vw.core.dto.error.BaseErrorReason;

public interface BaseErrorCode {
    public BaseErrorReason getErrorReason(); // 에러의 원인을 반환

    String getExplainError() throws NoSuchFieldException; // 예외에 대한 설명을 문자열로 반환
    // NoSuchFieldException : Reflection을 사용하여 Field를 접근하려고 할 때, 해당 필드가 존재하지 않는 경우에 발생
}
