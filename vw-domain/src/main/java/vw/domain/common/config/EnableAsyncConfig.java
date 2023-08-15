package vw.domain.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync // 스프링 비동기 처리 활성화
@Configuration // 해당 클래스를 스프링의 설정 클래스로 지정
public class EnableAsyncConfig implements AsyncConfigurer {
	// 'AsyncConfigurer' : 비동기 처리에 필요한 구성 요소를 제공
}
