package vw.api.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity

                // HTTP 기본 인증을 비활성화
                .httpBasic()
                .disable()
                // CORS (Cross-Origin Resource Sharing) 설정을 비활성화
                .cors()
                .disable()
                // CSRF (Cross-Site Request Forgery) 공격 방어를 비활성화
                .csrf()
                .disable()
                // 세션 관리를 상태 없음 (STATELESS)로 설정
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // 구성된 HttpSecurity 객체 반환
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }
}
