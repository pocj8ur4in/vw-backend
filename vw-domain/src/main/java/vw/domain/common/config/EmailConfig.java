package vw.domain.common.config;

import java.util.Properties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailConfig { // 메일 전송 기능에 관련된 설정 관리
	@Value("${spring.mail.host}")
	private String host;

	@Value("${spring.mail.port}")
	private int port;

	@Value("${spring.mail.username}")
	private String username;

	@Value("${spring.mail.password}")
	private String password;

	@Value("${spring.mail.protocol}")
	private String protocol;

	@Value("${spring.mail.default-encoding}")
	private String encoding;

	@Value("${spring.mail.properties.mail.smtp.auth}")
	private boolean auth;

	@Value("${spring.mail.properties.mail.smtp.ssl.enable}")
	private boolean ssl_enable;

	@Value("${spring.mail.properties.mail.smtp.ssl.trust}")
	private String ssl_trust;

	@Value("${spring.mail.properties.mail.smtp.starttls.enable}")
	private boolean starttls_enable;

	@Value("${spring.mail.properties.mail.smtp.starttls.required}")
	private boolean starttls_required;

	@Value("${spring.mail.properties.mail.smtp.timeout}")
	private int timeout;

	@Value("${spring.mail.properties.mail.smtp.socketFactory.class}")
	private String socketFactory_class;

	@Value("${spring.mail.properties.mail.smtp.socketFactory.fallback}")
	private boolean socketFactory_fallback;

	@Value("${spring.mail.properties.mail.smtp.socketFactory.port}")
	private int socketFactory_port;

	@Value("${spring.mail.properties.mail.debug}")
	private boolean debug;

	@Bean
	public JavaMailSender getJavaMailSender() { // JavaMail API으로 이메일을 전송하는 데 필요한 설정을 관리
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

		javaMailSender.setHost(host);
		javaMailSender.setPort(port);
		javaMailSender.setUsername(username);
		javaMailSender.setPassword(password);
		javaMailSender.setDefaultEncoding(encoding);
		javaMailSender.setProtocol(protocol);
		javaMailSender.setJavaMailProperties(getMailProperties());

		return javaMailSender;
	}

	private Properties getMailProperties() { // 이메일 클라이언트 또는 전송 서버와 관련된 속성을 설정
		Properties properties = new Properties();

		properties.put("mail.smtp.auth", auth);
		properties.put("mail.smtp.ssl.enable", ssl_enable);
		properties.put("mail.smtp.ssl.trust", ssl_trust);
		properties.put("mail.smtp.starttls.enable", starttls_enable);
		properties.put("mail.smtp.starttls.required", starttls_required);
		properties.put("mail.smtp.timeout", timeout);
		properties.put("mail.smtp.socketFactory.class", socketFactory_class);
		properties.put("mail.smtp.socketFactory.fallback", socketFactory_fallback);
		properties.put("mail.smtp.socketFactory.port", socketFactory_port);
		properties.put("mail.debug", debug);

		return properties;
	}
}
