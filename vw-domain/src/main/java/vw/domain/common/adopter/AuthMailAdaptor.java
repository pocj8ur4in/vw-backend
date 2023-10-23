package vw.domain.common.adopter;

import lombok.RequiredArgsConstructor;
import vw.core.annotation.Adaptor;
import vw.core.exception.authMail.*;
import vw.domain.common.entity.AuthMail;
import vw.domain.common.repository.AuthMailRepository;

@Adaptor
@RequiredArgsConstructor
public class AuthMailAdaptor { // 인증 이메일 레포지토리 어댑터
	private final AuthMailRepository authMailRepository;

	public void save(AuthMail authMail) { // 인증 이메일 정보 저장
		try {
			authMailRepository.save(authMail);
			authMailRepository.existsAuthMailByEmail(authMail.getEmail());
		} catch (Exception exception) {
			throw AuthMailSaveFailException.baseCodeException; // 인증 이메일 정보 저장이 실패한 경우
		}
	}

	public void update(AuthMail authMail1, AuthMail authMail2) { // 인증 이메일 정보 갱신
		try {
			authMailRepository.delete(authMail1);
			if (authMailRepository.existsAuthMailByEmail(authMail1.getEmail()))
				throw new RuntimeException(); // 이메일이 존재하는 경우
			authMailRepository.save(authMail2);
			if (!authMailRepository.existsAuthMailByEmail(authMail2.getEmail()))
				throw new RuntimeException(); // 이메일이 존재하지 않는 경우
		} catch (Exception exception) {
			throw AuthMailUpdateFailException.baseCodeException; // 인증 이메일 정보 갱신이 실패한 경우
		}
	}

	public void deleteByEmail(String email) { // 인증 이메일 정보 삭제
		try {
			AuthMail authMail =
					authMailRepository
							.findAuthMailByEmail(email)
							.orElseThrow(RuntimeException::new); // 삭제할 인증 이메일 정보가 없는 경우
			authMailRepository.delete(authMail);
			if (authMailRepository.existsAuthMailByEmail(email))
				throw new RuntimeException(); // 이메일이 존재하는 경우
		} catch (Exception exception) {
			throw AuthMailDeleteFailException.baseCodeException; // 인증 이메일 정보 삭제가 실패한 경우
		}
	}

	public void existsAuthMailByEmail(String email) { // 이메일로 인증 이메일 정보가 존재하는지 확인
		try {
			if (authMailRepository.existsAuthMailByEmail(email)) {
				throw new RuntimeException(); // 인증 이메일 정보가 존재하는 경우
			}
		} catch (Exception exception) {
			AuthMail authMail =
					authMailRepository
							.findAuthMailByEmail(email)
							.orElseThrow(RuntimeException::new);

			if (authMail.getAuthenticated() == Boolean.TRUE) {
				throw AlreadyEmailAuthException.baseCodeException; // 이미 인증된 이메일인 경우
			} else {
				throw AlreadyEmailSendException.baseCodeException; // 이미 전송된 이메일인 경우
			}
		}
	}

	public AuthMail findAuthMailByEmail(String email) { // 이메일로 인증 이메일 정보 탐색
		return authMailRepository
				.findAuthMailByEmail(email)
				.orElseThrow(
						() ->
								CouldNotFoundAuthMailException
										.baseCodeException); // 검색한 인증 이메일 정보가 존재하지 않는 경우
	}

	public AuthMail findAuthMailByAuthKey(String authKey) { // 인증 키로 인증 이메일 정보 탐색
		return authMailRepository
				.findAuthMailByAuthKey(authKey)
				.orElseThrow(
						() ->
								CouldNotFoundAuthMailException
										.baseCodeException); // 검색한 인증 이메일 정보가 존재하지 않는 경우
	}
}
