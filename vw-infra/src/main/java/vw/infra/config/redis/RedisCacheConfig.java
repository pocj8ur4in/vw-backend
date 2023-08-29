package vw.infra.config.redis;

import java.time.Duration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching // 캐시 기능 활성화
@Import(RedisConfig.class) // RedisConnectionFactory으로 CacheManager 설정
public class RedisCacheConfig {
	@Bean
	@Primary
	public CacheManager redisCacheManager(
			RedisConnectionFactory redisConnectionFactory) { // 기본 CacheManager 설정
		// redis 캐시의 기본 설정을 구성
		RedisCacheConfiguration redisCacheConfiguration =
				RedisCacheConfiguration.defaultCacheConfig()
						.serializeKeysWith( // redis에서 키를 String으로 직렬화하도록 설정
								RedisSerializationContext.SerializationPair.fromSerializer(
										new StringRedisSerializer()))
						.serializeValuesWith( // redis에서 값을 JSON 형태로 직렬화하도록 설정
								RedisSerializationContext.SerializationPair.fromSerializer(
										new GenericJackson2JsonRedisSerializer()))
						.entryTtl( // Cache의 기본 TTL (Time-To-Live)을 1시간으로 설정
								Duration.ofHours(1L));

		// RedisConnectionFactory를 사용하여 RedisCacheManager 생성
		return RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(
						redisConnectionFactory)
				.cacheDefaults(
						redisCacheConfiguration) // redis 캐시의 기본 설정을 지정한 RedisCacheConfiguration 설정
				.build();
	}

	// OIDC (OpenID Connect) : 인증 및 권한 부여를 위한 프로토콜
	@Bean
	public CacheManager oidcCacheManager(
			RedisConnectionFactory redisConnectionFactory) { // OIDC CacheManager 설정
		// OIDC Cache의 redis 캐시 설정을 구성
		RedisCacheConfiguration redisCacheConfiguration =
				RedisCacheConfiguration.defaultCacheConfig()
						.serializeKeysWith( // redis에서 키를 String으로 직렬화하도록 설정
								RedisSerializationContext.SerializationPair.fromSerializer(
										new StringRedisSerializer()))
						.serializeValuesWith( // redis에서 값을 JSON 형태로 직렬화하도록 설정
								RedisSerializationContext.SerializationPair.fromSerializer(
										new GenericJackson2JsonRedisSerializer()))
						.entryTtl( // OIDC Cache의 기본 TTL을 7일로 설정
								Duration.ofDays(7L));

		// RedisConnectionFactory를 사용하여 RedisCacheManager 생성
		return RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(
						redisConnectionFactory)
				.cacheDefaults(
						redisCacheConfiguration) // OIDC Cache 설정을 지정한 RedisCacheConfiguration으로 설정
				.build();
	}
}
