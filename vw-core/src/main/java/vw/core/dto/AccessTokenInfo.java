package vw.core.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccessTokenInfo {
    private final Long userId; // 사용자 아이디
    private final String role; // 사용자 역할 값
}
