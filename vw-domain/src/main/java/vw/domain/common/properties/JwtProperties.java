package vw.domain.common.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "custom.jwt") // prefix로 시작하는 properties 정보를 바인딩
public class JwtProperties {
	private String secretKey; // 시크릿 키
	private Long accessExp; // 만료 시간
	private Long refreshExp; // 갱신 시간
}
