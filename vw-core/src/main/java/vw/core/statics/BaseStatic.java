package vw.core.statics;

public class BaseStatic {
    // HTTP 응답 코드 4XX (클라이언트 오류)
    public static final int BAD_REQUEST = 400; // 잘못된 요청
    public static final int UNAUTHORIZED = 401; // 권한 없음
    public static final int FORBIDDEN = 403; // 거부됨
    public static final int NOT_FOUND = 404; // 찾을 수 없음

    // HTTP 응답 코드 5XX (서버 오류)
    public static final int INTERNAL_SERVER = 500; // 내부 서버 오류
}
