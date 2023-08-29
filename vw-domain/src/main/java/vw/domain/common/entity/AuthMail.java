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
public class AuthMail {
	@Id private Long id;
	@Indexed private String email;
	@Indexed private String authKey;
	@Indexed private Boolean authenticated;

	@TimeToLive private int ttl;

	@Builder
	public AuthMail(String email, String authKey, Boolean authenticated, int ttl) {
		this.email = email;
		this.authKey = authKey;
		this.authenticated = authenticated;
		this.ttl = ttl;
	}
}
