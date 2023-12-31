package vw.infra.config.redis;

import java.time.Duration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisKeyValueAdapter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableRedisRepositories( // redis와 관련된 Spring Data Repositories 활성화
		basePackages = "vw",
		enableKeyspaceEvents = RedisKeyValueAdapter.EnableKeyspaceEvents.ON_STARTUP)
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

	@Bean // RedisTemplate : Redis 데이터를 처리하는 데 사용 (RedisTemplate을 Bean으로 생성)
	public RedisTemplate<?, ?> redisTemplate() { // key, value에 해당하는 제네릭 타입을 와일드카드(?)로 선언
		RedisTemplate<byte[], byte[]> redisTemplate =
				new RedisTemplate<>(); // Redis에 바이트 배열로 직접 데이터 저장

		// 일반적인 key:value일 경우에 직렬화
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new StringRedisSerializer());

		// Hash를 사용할 경우에 직렬화
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashValueSerializer(new StringRedisSerializer());

		// 모든 경우
		redisTemplate.setConnectionFactory(redisConnectionFactory());

		return redisTemplate; // RedisConnection에서 넘겨준 byte 값 객체 직렬화
	}
}
