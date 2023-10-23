package vw.domain.common.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisUtils { // 레디스 유틸리티
	private final RedisTemplate<byte[], byte[]> redisTemplate;

	public void setData(byte[] key, byte[] value, int expiredTime) {
		redisTemplate.opsForValue().set(key, value, expiredTime);
	}

	public byte[] getData(byte[] key) {
		return redisTemplate.opsForValue().get(key);
	}

	public void deleteData(byte[] key) {
		redisTemplate.delete(key);
	}
}
