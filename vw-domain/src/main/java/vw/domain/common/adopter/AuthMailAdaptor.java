package vw.domain.common.adopter;

import lombok.RequiredArgsConstructor;
import vw.core.annotation.Adaptor;
import vw.core.exception.authMail.AlreadyEmailAuthException;
import vw.core.exception.authMail.AlreadyEmailSendException;
import vw.core.exception.authMail.EmailSendFailException;
import vw.core.exception.authMail.InvalidAuthKeyException;
import vw.core.exception.user.RegisterFailureException;
import vw.domain.common.entity.AuthMail;
import vw.domain.common.repository.AuthMailRepository;

@Adaptor
@RequiredArgsConstructor
public class AuthMailAdaptor {
	private final AuthMailRepository authMailRepository;

	public void save(AuthMail authMail) {
		try {
			authMailRepository.save(authMail);
			authMailRepository.existsAuthMailByEmail(authMail.getEmail());
		} catch (Exception exception) {
			throw EmailSendFailException.baseCodeException;
		}
	}

	public void update(AuthMail authMail1, AuthMail authMail2) {
		try {
			authMailRepository.delete(authMail1);
			notExistsAuthMailByEmail(authMail1.getEmail());
			authMailRepository.save(authMail2);
			authMailRepository.existsAuthMailByEmail(authMail2.getEmail());
		} catch (Exception exception) {
			throw InvalidAuthKeyException.baseCodeException;
		}
	}

	public void deleteByEmail(String email) {
		try {
			AuthMail authMail = findAuthMailByEmail(email);
			authMailRepository.delete(authMail);
			notExistsAuthMailByEmail(email);
		} catch (Exception exception) {
			throw RegisterFailureException.baseCodeException;
		}
	}

	public void existsAuthMailByEmail(String email) {
		if (authMailRepository.existsAuthMailByEmail(email)) {
			if (!authMailRepository.findAuthMailByEmail(email).get().getAuthenticated())
				throw AlreadyEmailSendException.baseCodeException;
			else throw AlreadyEmailAuthException.baseCodeException;
		}
	}

	public void notExistsAuthMailByEmail(String email) {
		if (authMailRepository.existsAuthMailByEmail(email))
			throw EmailSendFailException.baseCodeException;
	}

	public AuthMail findAuthMailByEmail(String email) {
		return authMailRepository
				.findAuthMailByEmail(email)
				.orElseThrow(() -> RegisterFailureException.baseCodeException);
	}

	public AuthMail findAuthMailByAuthKey(String authKey) {
		return authMailRepository
				.findAuthMailByAuthKey(authKey)
				.orElseThrow(() -> InvalidAuthKeyException.baseCodeException);
	}
}
