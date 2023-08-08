package vw.api.config;

import static org.springframework.security.config.Customizer.withDefaults;

import jakarta.servlet.DispatcherType;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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
                .authorizeHttpRequests(
                        request ->
                                request.dispatcherTypeMatchers(DispatcherType.FORWARD)
                                        .permitAll()
                                        .anyRequest()
                                        .permitAll()
                        // .requestMatchers().authenticated() // 인증 필요한 영역 생기면 추가
                        )
                .formLogin(
                        login ->
                                login.loginPage("/user/login") // 로그인 페이지
                                        .usernameParameter("username") // 아이디
                                        .passwordParameter("password") // 비밀번호
                                        .defaultSuccessUrl("/", true) // 회원가입 성공 시 이동할 주소
                                        .permitAll())
                .logout(withDefaults());

        // 구성된 HttpSecurity 객체 반환
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }
}
