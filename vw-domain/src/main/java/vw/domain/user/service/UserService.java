package vw.domain.user.service;

import static vw.core.statics.BaseStatic.*;

import java.util.Objects;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import vw.core.exception.user.*;
import vw.domain.user.adaptor.UserAdaptor;
import vw.domain.user.entity.*;

@Service
@RequiredArgsConstructor
public class UserService { // 회원 서비스
	private final UserAdaptor userAdaptor;

	private static final Logger logger =
			LoggerFactory.getLogger(UserService.class); // SLF4J를 활용한 로그 기록

	public void chkEmptyId(String id) { // 아이디 공란 여부 확인
		logger.info(">> 아이디 공란 여부 확인");

		if (id == null || id.isEmpty()) {
			throw EmptyIdException.baseCodeException; // 아이디가 공란인 경우
		}
	}

	public void chkLengthId(String id) { // 아이디 길이 확인
		logger.info(">> 아이디 길이 확인");

		Pattern pattern = Pattern.compile(ID_PATTERN_LENGTH);

		if (!pattern.matcher(id).matches()) {
			throw IncorrectIdLengthException.baseCodeException; // 아이디 길이가 잘못된 경우
		}
	}

	public void chkPatternId(String id) { // 아이디 형식 확인
		logger.info(">> 아이디 형식 확인");

		if (!Pattern.matches(ID_PATTERN, id)) {
			throw IncorrectIdException.baseCodeException; // 아이디 형식이 잘못된 경우
		}
	}

	public void chkAlreadyExistId(String id) { // 아이디 중복 여부 확인
		logger.info(">> 아이디 중복 여부 확인");

		userAdaptor.existsUserByUserAuth_Id(id);
	}

	public void chkEmptyNickname(String nickname) { // 닉네임 공란 여부 확인
		logger.info(">> 닉네임 공란 여부 확인");

		if (nickname == null || nickname.isEmpty()) {
			throw EmptyNicknameException.baseCodeException; // 닉네임이 공란인 경우
		}
	}

	public void chkLengthNickname(String nickname) { // 닉네임 길이 확인
		logger.info(">> 닉네임 길이 확인");

		Pattern pattern = Pattern.compile(NICKNAME_PATTERN_LENGTH);

		if (!pattern.matcher(nickname).matches()) {
			throw IncorrectNicknameLengthException.baseCodeException; // 닉네임 길이가 잘못된 경우
		}
	}

	public void chkPatterNickname(String nickname) { // 닉네임 형식 확인
		logger.info(">> 닉네임 형식 확인");

		if (!Pattern.matches(NICKNAME_PATTERN, nickname)) {
			throw IncorrectNicknameException.baseCodeException; // 닉네임 형식이 잘못된 경우
		}
	}

	public void chkAlreadyExistNickname(String nickname) { // 닉네임 중복 여부 확인
		logger.info(">> 닉네임 중복 여부 확인");

		userAdaptor.existsUserByUserProfile_Nickname(nickname);
	}

	public void chkEmptyEmail(String email) { // 이메일 공란 여부 확인
		logger.info(">> 이메일 공란 여부 확인");

		if (email == null || email.isEmpty()) {
			throw EmptyEmailException.baseCodeException; // 이메일이 공란인 경우
		}
	}

	public void chkPatternEmail(String email) { // 이메일 형식 확인
		logger.info(">> 이메일 형식 확인");

		if (!Pattern.matches(EMAIL_PATTERN, email)) {
			throw IncorrectEmailException.baseCodeException; // 이메일 형식이 잘못된 경우
		}
	}

	public void chkAlreadyExistEmail(String email) { // 이메일 중복 여부 확인
		logger.info(">> 이메일 중복 여부 확인");

		userAdaptor.existsUserByUserProfile_Email(email);
	}

	public void register(
			String id,
			String password,
			String nickname,
			String email,
			Boolean receiveEmail) { // 회원가입
		logger.info(">> 회원가입");

		userAdaptor.save(
				User.builder()
						.userType(UserType.TYPE_NORMAL)
						.userState(UserState.STATE_NORMAL)
						.userAuth(UserAuth.builder().id(id).password(password).build())
						.userProfile(UserProfile.builder().nickname(nickname).email(email).build())
						.userToogle(UserToogle.builder().receiveEmail(receiveEmail).build())
						.build()); // 사용자를 저장소에 저장
	}

	public long login(String id, String password) { // 일반 로그인
		logger.info(">> 일반 로그인");

		User user = userAdaptor.findUserByUserAuth_Id(id);

		if (!Objects.equals(user.getUserAuth().getPassword(), password)) {
			throw UnMatchPasswordException.baseCodeException; // 입력한 비밀번호가 틀린 경우
		}

		return user.getIndex();
	}
}
