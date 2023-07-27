package vw.core.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import vw.core.properties.JwtProperties;

@EnableConfigurationProperties({JwtProperties.class}) // Spring 컨텍스트에서 properties 클래스를 Bean으로 등록
@Configuration
public class ConfigurationPropertiesConfig {} // 애플리케이션에서 사용되는 properties를 자동으로 구성
