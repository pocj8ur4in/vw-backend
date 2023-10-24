package vw.domain.common.handler;

import static vw.core.statics.BaseStatic.*;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import vw.core.dto.jwt.AccessTokenRequest;
import vw.core.exception.jwt.ExpiredTokenException;
import vw.core.exception.jwt.InvalidTokenException;
import vw.domain.common.properties.JwtProperties;

@RequiredArgsConstructor
@Component
public class JwtHandler { // JWT 핸들러
	private static final Logger logger =
			LoggerFactory.getLogger(JwtHandler.class); // SLF4J를 활용한 로그 기록
	private final JwtProperties jwtProperties;

	public String generateAccessToken(Long id) { // AccessToken 생성
		logger.info(">> AccessToken 생성");

		final Date issuedAt = new Date();
		final Date accessTokenExpiresIn =
				new Date(
						issuedAt.getTime()
								+ jwtProperties.getAccessExp() * MILLI_TO_SECOND); // 토큰 발행 일시 설정

		return buildAccessToken(id, issuedAt, accessTokenExpiresIn); // AccessToken을 생성해 반환
	}

	public String generateRefreshToken(Long id) { // RefreshToken 생성
		logger.info(">> RefreshToken 생성");

		final Date issuedAt = new Date();
		final Date refreshTokenExpiresIn =
				new Date(
						issuedAt.getTime()
								+ jwtProperties.getRefreshExp() * MILLI_TO_SECOND); // 토큰 발행 일시 설정

		return buildRefreshToken(id, issuedAt, refreshTokenExpiresIn); // RefreshToken을 생성해 반환
	}

	public boolean isAccessToken(String token) { // 주어진 JWT 토큰이 AccessToken인지 여부 확인
		logger.info(">> 주어진 JWT 토큰이 AccessToken인지 여부 확인");

		// getJws을 통해 주어진 토큰을 파싱하고, JWT의 Body인 Claims를 가져옴
		return getJws(token)
				.getBody()
				.get(TOKEN_TYPE)
				.equals(ACCESS_TOKEN); // 가져온 토큰 타입이 ACCESS_TOKEN과 같은지 비교
	}

	public boolean isRefreshToken(String token) { // 주어진 JWT 토큰이 RefreshToken인지 여부 확인
		logger.info(">> 주어진 JWT 토큰이 RefreshToken인지 여부 확인");

		// getJws을 통해 주어진 토큰을 파싱하고, JWT의 Body인 Claims를 가져옴
		return getJws(token)
				.getBody()
				.get(TOKEN_TYPE)
				.equals(REFRESH_TOKEN); // 가져온 토큰 타입이 REFRESH_TOKEN과 같은지 비교
	}

	public AccessTokenRequest parseAccessToken(String token) { // 주어진 AccessToken을 파싱하여 정보 추출
		logger.info(">> 주어진 AccessToken을 파싱하여 정보 추출");

		if (isAccessToken(token)) { // 토큰 타입을 확인하여 AccessToken인지 판단
			Claims claims = getJws(token).getBody(); // 해당 토큰을 파싱하고, JWT의 Body인 Claims를 가져옴

			//  Claims에서 추출한 정보를 AccessTokenRequest 객체로 빌드하여 반환
			return AccessTokenRequest.builder()
					.userId(Long.parseLong(claims.getSubject()))
					.role((String) claims.get(TOKEN_ROLE))
					.build();
		}
		// 주어진 토큰이 Access Token이 아닌 경우
		throw InvalidTokenException.baseCodeException;
	}

	public Long parseRefreshToken(String token) { // 주어진 RefreshToken을 파싱하여 정보 추출
		logger.info(">> 주어진 RefreshToken을 파싱하여 정보 추출");

		try {
			if (isRefreshToken(token)) { // 토큰 타입을 확인하여 RefreshToken인지 판단
				Claims claims = getJws(token).getBody(); // 해당 토큰을 파싱하고, JWT의 Body인 Claims를 가져옴

				// Claims에서 추출한 userId를 문자열에서 Long 타입으로 변환하여 반환
				return Long.parseLong(claims.getSubject());
			}
		} catch (ExpiredTokenException expiredTokenException) { // 토큰이 만료되었을 경우
			throw ExpiredTokenException.baseCodeException;
		}
		// 주어진 토큰이 Access Token이 아닌 경우
		throw InvalidTokenException.baseCodeException;
	}

	public Long getRefreshTokenTTlSecond() { // RefreshToken의 TTL을 초 단위로 반환
		logger.info(">> RefreshToken의 TTL을 초 단위로 반환");

		return jwtProperties.getRefreshExp();
	}

	public Long getAccessTokenTTlSecond() { // AccessToken의 TTL을 초 단위로 반환
		logger.info(">> AccessToken의 TTL을 초 단위로 반환");

		return jwtProperties.getAccessExp();
	}

	private Jws<Claims> getJws(String token) { // JWT 토큰을 파싱하여 Claims 정보 추출
		logger.info(">> JWT 토큰을 파싱하여 Claims 정보 추출");

		// Jws : JWT의 서명이 검증된 토큰을 나타내는 객체
		// Claims : JWT의 Claims 정보를 담고 있는 객체
		try {
			// JWT 파서를 빌드하고 해당 JWT 토큰의 서명을 검증하기 위해 SigningKey 설정
			// parseClaimsJws 메서드를 사용하여 토큰을 파싱하고 서명이 검증된 Jws<Claims> 객체 반환
			return Jwts.parserBuilder().setSigningKey(getSecretKey()).build().parseClaimsJws(token);
		} catch (ExpiredJwtException expiredJwtException) { // 토큰이 만료되었을 경우
			throw ExpiredTokenException.baseCodeException;
		} catch (Exception invalidTokenException) { // 토큰이 만료되었을 경우 외에 예외가 발생한 경우
			throw InvalidTokenException.baseCodeException;
		}
	}

	private Key getSecretKey() { // 시크릿 키를 가져옴
		logger.info(">> 시크릿 키를 가져옴");

		return Keys.secretKeyFor(SignatureAlgorithm.HS256);
	}

	private String buildAccessToken(
			Long id, Date issuedAt, Date accessTokenExpiresIn) { // AccessToken 빌드해 반환
		logger.info(">> AccessToken 빌드해 반환");

		final Key encodedKey = getSecretKey(); // JWT 토큰에 서명하는 데 사용할 시크릿 키 가져옴

		return Jwts.builder() // JWT 빌더를 사용하여 JWT 토큰을 구성
				.setIssuer(TOKEN_ISSUER) // 토큰 발행자 설정
				.setIssuedAt(issuedAt) // 토큰 발행 일시 설정
				.setSubject(id.toString()) // 토큰 주제 설정 : 사용자의 ID를 문자열로 변환해 설정
				.claim(TOKEN_TYPE, ACCESS_TOKEN) // JWT의 Claim에 TOKEN_TYPE과 ACCESS_TOKEN 추가
				.setExpiration(accessTokenExpiresIn) // 토큰 만료 일시 설정
				.signWith(encodedKey) // 시크릿 키를 사용하여 토큰에 서명
				.compact(); // JWT 토큰을 문자열로 변환하여 반환
	}

	private String buildRefreshToken(
			Long id, Date issuedAt, Date accessTokenExpiresIn) { // RefreshToken 빌드해 반환
		logger.info(">> RefreshToken 빌드해 반환");

		final Key encodedKey = getSecretKey(); // JWT 토큰에 서명하는 데 사용할 시크릿 키 가져옴

		return Jwts.builder() // JWT 빌더를 사용하여 JWT 토큰을 구성
				.setIssuer(TOKEN_ISSUER) // 토큰 발행자 설정
				.setIssuedAt(issuedAt) // 토큰 발행 일시 설정
				.setSubject(id.toString()) // 토큰 주제 설정 : 사용자의 ID를 문자열로 변환해 설정
				.claim(TOKEN_TYPE, REFRESH_TOKEN) // JWT의 Claim에 TOKEN_TYPE과 REFRESH_TOKEN 추가
				.setExpiration(accessTokenExpiresIn) // 토큰 만료 일시 설정
				.signWith(encodedKey) // 시크릿 키를 사용하여 토큰에 서명
				.compact(); // JWT 토큰을 문자열로 변환하여 반환
	}
}
