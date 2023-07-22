package vw.infra.config.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {
    @Value("${spring.data.redis.host}")
    private String redisHost; // redis 호스트 주소

    @Value("${spring.data.redis.port}")
    private int redisPort; // redis 포트 번호

    private static final String REDISSON_HOST_PREFIX =
            "redis://"; // redisson에서 Redis 호스트 주소에 필요한 프로토콜 문자열

    @Bean
    public RedissonClient redissonClient() { // redis 클라이언트를 구성
        Config config = new Config(); // Config 객체 생성

        config.useSingleServer()
                .setAddress(
                        REDISSON_HOST_PREFIX
                                + redisHost
                                + ":"
                                + redisPort); // redisson의 SingleServerConfig 설정

        return Redisson.create(config);
    }
}
