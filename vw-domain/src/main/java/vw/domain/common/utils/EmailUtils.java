package vw.domain.common.utils;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailUtils { // 이메일 유틸리티
	private final JavaMailSender javaMailSender;
	private final MimeMessage mimeMessage;
	private final MimeMessageHelper mimeMessageHelper;

	public EmailUtils(JavaMailSender javaMailSender) throws MessagingException {
		this.javaMailSender = javaMailSender;
		mimeMessage = this.javaMailSender.createMimeMessage();
		mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
	}

	public void setSubject(String subject) throws MessagingException {
		mimeMessageHelper.setSubject(subject);
	}

	public void setText(String htmlContent) throws MessagingException {
		mimeMessageHelper.setText(htmlContent, true);
	}

	public void setFrom(InternetAddress internetAddress) throws MessagingException {
		mimeMessageHelper.setFrom(internetAddress);
	}

	public void setTo(String email) throws MessagingException {
		mimeMessageHelper.setTo(email);
	}

	public void send() throws MailSendException {
		javaMailSender.send(mimeMessage);
	}
}
