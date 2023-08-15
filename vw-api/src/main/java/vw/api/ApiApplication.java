package vw.api;

import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.filter.ForwardedHeaderFilter;

@RequiredArgsConstructor
@SpringBootApplication( // '@SpringBootApplication' : 스프링 부트 애플리케이션의 주요 구성 요소들을 자동으로 설정
		scanBasePackages = { // 'scanBasePackages={}' : Spring이 Component Scan을 수행할 패키지들을 지정
			"vw.api",
			"vw.domain"
		})
@Slf4j
public class ApiApplication
		implements ApplicationListener<ApplicationReadyEvent> { // 애플리케이션이 초기화되고 준비된 이후에 발생하는 이벤트 감지

	private final Environment environment; // 환경 설정과 관련된 정보 제공

	public static void main(String[] args) {
		System.setProperty( // 'System.setProperty' : 스프링 부트 애플리케이션의 환경 설정 파일 이름을 지정
				"spring.config.name",
				"application, application-domain, application-core, application-infra");

		SpringApplication.run(ApiApplication.class, args);
	}

	@Bean
	ForwardedHeaderFilter forwardedHeaderFilter() { // 요청 헤더를 통해 프록시 서버에서 전달된 정보를 처리
		return new ForwardedHeaderFilter();
	}

	@Override
	public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
		// ApplicationReadyEvent가 발생하면 해당 메소드가 호출 : 활성화된 프로파일들을 로그로 출력
		log.info("applicationReady status" + Arrays.toString(environment.getActiveProfiles()));
	}
}
