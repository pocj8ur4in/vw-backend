package vw.core.statics;

public class BaseStatic {
    // JWT 토큰 관련
    public static final int MILLI_TO_SECOND = 1000; // 1초 = 1000밀리초
    public static final String TOKEN_ROLE = "ROLE";
    public static final String TOKEN_TYPE = "TYPE";
    public static final String TOKEN_ISSUER = "VOCAWIK";
    public static final String ACCESS_TOKEN = "ACCESS_TOKEN";
    public static final String REFRESH_TOKEN = "REFRESH_TOKEN";

    // HTTP 응답 코드 4XX (클라이언트 오류)
    public static final int BAD_REQUEST = 400; // 잘못된 요청
    public static final int UNAUTHORIZED = 401; // 권한 없음
    public static final int FORBIDDEN = 403; // 거부됨
    public static final int NOT_FOUND = 404; // 찾을 수 없음

    // HTTP 응답 코드 5XX (서버 오류)
    public static final int INTERNAL_SERVER = 500; // 내부 서버 오류
}
