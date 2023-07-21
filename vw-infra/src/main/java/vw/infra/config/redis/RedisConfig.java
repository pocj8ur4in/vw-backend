package vw.infra.config.redis;

import java.time.Duration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableRedisRepositories( // redis와 관련된 Spring Data Repositories 활성화
        basePackages = "vw")
public class RedisConfig {
    @Value("${spring.data.redis.host}")
    private String redisHost; // redis 호스트 주소

    @Value("${spring.data.redis.port}")
    private int redisPort; // redis 포트 번호

    @Value("${spring.data.redis.password}")
    private String redisPassword; // redis 비밀번호

    @Bean // Redis 연결 설정을 위한 Bean 생성
    public RedisConnectionFactory
            redisConnectionFactory() { // RedisConnectionFactory : redis와의 연결을 관리하는 인터페이스
        RedisStandaloneConfiguration redisStandaloneConfiguration =
                new RedisStandaloneConfiguration(redisHost, redisPort);

        if (redisPassword != null && !redisPassword.isBlank()) // redis에 비밀번호가 설정되어 있지 않으면 비밀번호를 설정
        redisStandaloneConfiguration.setPassword(redisPassword);

        // LettuceClientConfiguration을 생성하여 Redis 연결 설정 구성
        LettuceClientConfiguration lettuceClientConfiguration =
                LettuceClientConfiguration.builder() // LettuceClientConfiguration의 builder 패턴 생성
                        .commandTimeout(Duration.ofSeconds(1)) // redis 명령의 최대 실행 시간을 1초로 설정
                        .shutdownTimeout(Duration.ZERO) // Redis 클라이언트의 종료 시간을 0초로 설정 (무한 대기)
                        .build();

        // LettuceConnectionFactory를 생성하여 Redis 연결 수립
        return new LettuceConnectionFactory(
                redisStandaloneConfiguration, lettuceClientConfiguration); // 내장 혹은 외부의 redis 연결
    }

    @Bean // RedisTemplate을 Bean으로 생성
    public RedisTemplate<?, ?> redisTemplate() { // RedisTemplate : Redis 데이터를 처리하는 데 사용
        RedisTemplate<byte[], byte[]> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());

        // 일반적인 key:value일 경우에 직렬화
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());

        // Hash를 사용할 경우에 직렬화
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());

        return redisTemplate; // RedisConnection에서 넘겨준 byte 값 객체 직렬화
    }
}
