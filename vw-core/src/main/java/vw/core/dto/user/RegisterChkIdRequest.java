package vw.core.dto.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegisterChkIdRequest {
	private String id; // 회원 아이디
}
