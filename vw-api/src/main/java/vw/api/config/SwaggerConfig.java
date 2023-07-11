package vw.api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 제목, 버전과 같은 OpenAPI 문서의 정보 정의
@OpenAPIDefinition(info = @Info(title = "vocawik api", version = "v1"))
@RequiredArgsConstructor
@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi groupedOpenApi() {
        String[] paths = {"/v1/**"};

        // GroupedOpenApi : 그룹화된 OpenAPI 스펙을 담은 객체
        return GroupedOpenApi.builder().group("vocawik").pathsToMatch(paths).build();
    }
}
