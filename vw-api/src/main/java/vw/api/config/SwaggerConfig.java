package vw.api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(info = @Info(title = "vocawik api", version = "v1")) // 제목, 버전과 같은 OpenAPI 문서 정의
@Configuration
@RequiredArgsConstructor
public class SwaggerConfig { // Swagger3 설정
	@Bean
	public GroupedOpenApi groupedOpenApi() {
		String[] paths = {"/**"}; // Swagger 경로 지정

		// GroupedOpenApi : 그룹화된 OpenAPI 스펙을 담은 객체
		return GroupedOpenApi.builder().group("vocawik").pathsToMatch(paths).build();
	}
}
