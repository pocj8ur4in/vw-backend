package vw.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication( // '@SpringBootApplication' : 스프링 부트 애플리케이션의 주요 구성 요소들을 자동으로 설정
        scanBasePackages = { // 'scanBasePackages={}' : Spring이 Component Scan을 수행할 패키지들을 지정
            "vw.api",
            "vw.domain",
            "vw.infra"
        })
public class ApiApplication {
    public static void main(String[] args) {
        System.setProperty( // 'System.setProperty' : 스프링 부트 애플리케이션의 환경 설정 파일 이름을 지정
                "spring.config.name",
                "application, application-domain, application-core, application-infra");

        SpringApplication.run(ApiApplication.class, args);
    }
}
