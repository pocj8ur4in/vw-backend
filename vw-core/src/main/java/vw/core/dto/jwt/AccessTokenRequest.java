package vw.core.dto.jwt;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
/* @Data를 사용하지 않는 이유?
- 불필요한 메서드 생성
- 부적절할 수 있는 equals()와 hashCode()
- toString()이 디버깅 시에 너무 많은 정보을 노출시킬 수 있음
*/
public class AccessTokenRequest {
	private final Long userId; // 사용자 아이디
	private final String role; // 사용자 역할 값
}
