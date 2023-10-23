package vw.domain.common.entity;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@RedisHash(value = "auth_email")
public class AuthMail { // 이메일 인증 엔티티
	@Id private Long id; // 이메일 인증 식별자
	@Indexed private String email; // 이메일 인증 이메일
	@Indexed private String authKey; // 이메일 인증 키
	@Indexed private Boolean authenticated; // 이메일 인증 여부
	@TimeToLive private int ttl; // 이메일 인증 유효 시간

	@Builder
	public AuthMail(String email, String authKey, Boolean authenticated, int ttl) {
		this.email = email;
		this.authKey = authKey;
		this.authenticated = authenticated;
		this.ttl = ttl;
	}
}
