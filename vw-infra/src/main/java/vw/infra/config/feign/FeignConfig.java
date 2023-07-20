package vw.infra.config.feign;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import feign.Logger;
import feign.Retryer;
import feign.codec.Decoder;
import feign.jackson.JacksonDecoder;
import java.util.concurrent.TimeUnit;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vw.infra.outer.api.BaseFeignClientPackage;

@Configuration
@EnableFeignClients(
        basePackageClasses = BaseFeignClientPackage.class) // 해당 클래스가 위치한 패키지 밑에서 Feign 클라이언트를 탐색
public class FeignConfig { //  Feign 클라이언트가 응답을 디코딩할 때 사용할 디코더 정의
    @Bean
    public Decoder feignDecoder() {
        return new JacksonDecoder(
                customObjectMapper()); // JSON 응답을 객체로 디코딩하기 위한 JacksonDecoder를 사용하도록 설정
    }

    public ObjectMapper customObjectMapper() { // ObjectMapper 객체를 커스터마이징 (객체 직렬화/역직렬화 설정을 변경)
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.registerModule(
                new JavaTimeModule()); // 시간 관련 클래스들을 JSON으로 변환하고, JSON 데이터를 해당 클래스들로 역직렬화 가능하게 함

        // JSON 직렬화 시에, Java Date나 Calendar 객체를 타임스탬프 형태로 출력하지 않음 -> 일반적인 문자열 형태로 날짜를 표시
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        // JSON 역직렬화 시에, 알 수 없는 필드가 포함되어 있을 경우에도 역직렬화 작업을 진행시킴 -> 해당 필드를 무시하고 나머지 필드들을 역직렬화
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // JSON 역직렬화 시에, 날짜와 시간을 JSON 데이터에 포함된 그대로의 타임존으로 유지
        objectMapper.configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, false);

        // JSON 역직렬화 시에, 알 수 없는 enum 값을 null로 처리함
        objectMapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);

        return objectMapper; // 커스텀마이징한 ObjectMapper 객체를 반환
    }

    @Bean
    Retryer.Default retryer() { // 재시도 (Retry) 설정
        return new Retryer.Default(
                100L,
                TimeUnit.SECONDS.toMillis(3L),
                5); // 0.1초의 간격으로 시작해 최대 3초의 간격으로 점점 증가하며, 최대5번 재시도
    }

    @Bean
    Logger.Level feignLoggerLevel() { // Feign의 로그 레벨을 설정
        return Logger.Level.FULL; // FULL 레벨로 설정해 모든 요청과 응답에 대한 자세한 로그를 출력
    }
}
