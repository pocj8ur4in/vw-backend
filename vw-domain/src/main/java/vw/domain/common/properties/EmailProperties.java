package vw.domain.common.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "custom.email")
public class EmailProperties {
	private String address; // 발송 주소
	private String email; // 발송 이메일
	private String name; // 발송자 이름
}
