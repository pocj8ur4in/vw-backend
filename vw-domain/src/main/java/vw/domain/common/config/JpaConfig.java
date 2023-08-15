package vw.domain.common.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import vw.domain.DomainPackageLocation;

@Configuration
@EnableJpaAuditing
@EntityScan(basePackageClasses = DomainPackageLocation.class) // // Entity 스캔을 위한 기본 패키지 위치를 설정
@EnableJpaRepositories(
		basePackageClasses = DomainPackageLocation.class) // JPA 저장소를 활성화하기 위한 기본 패키지 위치를 설정
public class JpaConfig { // JPA Auditing 활성화
}
