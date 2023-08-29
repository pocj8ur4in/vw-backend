package vw.domain.common.handler;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;
import java.util.Objects;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import vw.core.exception.authMail.EmailSendFailException;
import vw.core.exception.authMail.InvalidAuthKeyException;
import vw.core.exception.authMail.InvalidEmailException;
import vw.core.exception.user.RegisterFailureException;
import vw.domain.common.adopter.AuthMailAdaptor;
import vw.domain.common.entity.AuthMail;
import vw.domain.common.properties.EmailProperties;
import vw.domain.common.utils.EmailUtils;

@Component
@RequiredArgsConstructor
public class EmailHandler {
	private static final Logger logger =
			LoggerFactory.getLogger(EmailHandler.class); // SLF4J를 활용한 로그 기록
	private final EmailProperties emailProperties;
	private final AuthMailAdaptor authMailAdaptor;
	@Autowired JavaMailSender javaMailSender;

	public void sendAuthEmail(String email) { // 인증 메일 전송
		try {
			String authKey = createAuthKey();
			saveAuthkey(email, authKey);
			EmailUtils emailUtils = createAuthEmail(email, authKey);
			sendMail(emailUtils);
		} catch (Exception exception) {
			logger.info(">>> 이메일 전송 실패");

			authMailAdaptor.deleteByEmail(email);
			throw EmailSendFailException.baseCodeException;
		}
	}

	public void authAuthKey(String email, String authKey) { // 이메일 인증 활성화
		try {
			AuthMail authMail = chkAuthkey(authKey);
			chkUnauthenticated(authMail.getAuthenticated());
			chkAuthMailByEmail(authMail.getEmail(), email);
			validateAuthKey(authMail);
		} catch (Exception exception) {
			logger.info(">>> 이메일 인증 활성화 실패");

			throw exception;
		}
	}

	public void chkAuth(String email) { // 이메일 인증 활성화 여부 확인
		logger.info(">> 이메일 인증 활성화 여부 확인");

		try {
			AuthMail authMail = authMailAdaptor.findAuthMailByEmail(email);
			if (authMail.getAuthenticated() == Boolean.FALSE)
				throw RegisterFailureException.baseCodeException;
		} catch (Exception exception) {
			throw RegisterFailureException.baseCodeException;
		}
	}

	public void deleteAuth(String email) { // 이메일 인증 비활성화
		logger.info(">> 이메일 인증 비활성화");

		authMailAdaptor.deleteByEmail(email);
	}

	public void chkAlreadySendEmail(String email) { // 인증 메일 전송 여부 확인
		logger.info(">> 인증 메일 전송 여부 확인");

		authMailAdaptor.existsAuthMailByEmail(email);
	}

	private String createAuthKey() {
		logger.info(">> 인증 키 생성");

		int num = 0;

		Random random = new Random();
		StringBuilder stringBuilder = new StringBuilder();

		do {
			num = random.nextInt(75) + 48;

			if ((num >= 48 && num <= 57) || (num >= 65 && num <= 90) || (num >= 97 && num <= 122)) {
				stringBuilder.append((char) num);
			} else {
				continue;
			}
		} while (stringBuilder.length() < 64);

		return stringBuilder.toString();
	}

	private void saveAuthkey(String email, String authKey) {
		logger.info(">> 인증 키 저장");

		authMailAdaptor.save(
				AuthMail.builder()
						.email(email)
						.authKey(authKey)
						.authenticated(false)
						.ttl(300)
						.build());
	}

	private EmailUtils createAuthEmail(String email, String authKey) throws MessagingException {
		logger.info(">> 인증 메일 생성");

		EmailUtils emailUtils = new EmailUtils(javaMailSender);

		emailUtils.setSubject("[인증 이메일]");
		emailUtils.setText(
				"<h1>[이메일 인증]</h1><br>"
						+ "<p>아래 링크를 클릭하면, 이메일 인증이 완료됩니다.</p><br>"
						+ "<a href='"
						+ emailProperties.getAddress()
						+ "/user/auth?email="
						+ email
						+ "&key="
						+ authKey
						+ "' target='_blank'>이메일 인증 확인</a>");
		emailUtils.setTo(email);

		return emailUtils;
	}

	private void sendMail(EmailUtils emailUtils)
			throws MessagingException, UnsupportedEncodingException {
		logger.info(">> 인증 메일 전송");

		emailUtils.setFrom(
				new InternetAddress(emailProperties.getEmail(), emailProperties.getName()));
		emailUtils.send();
	}

	private AuthMail chkAuthkey(String authKey) {
		logger.info(">> 인증 키를 통해 인증 정보 불러옴");

		return authMailAdaptor.findAuthMailByAuthKey(authKey);
	}

	private void chkUnauthenticated(Boolean authenticated) {
		logger.info(">> 인증 정보가 인증되지 않았는지 확인");

		if (authenticated != Boolean.FALSE) {
			throw InvalidAuthKeyException.baseCodeException;
		}
	}

	private void chkAuthMailByEmail(String email1, String email2) {
		logger.info(">> 불러온 & 전송된 인증 이메일 비교");

		if (!Objects.equals(email1, email2)) throw InvalidEmailException.baseCodeException;
	}

	private void validateAuthKey(AuthMail authMail) {
		logger.info(">> 인증 정보 활성화");

		authMailAdaptor.update(
				authMail,
				AuthMail.builder()
						.email(authMail.getEmail())
						.authKey(authMail.getAuthKey())
						.authenticated(true)
						.ttl(3600)
						.build());
	}
}
