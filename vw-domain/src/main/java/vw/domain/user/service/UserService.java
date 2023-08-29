package vw.domain.user.service;

import static vw.core.statics.BaseStatic.*;

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
public class UserService {
	private final UserAdaptor userAdaptor;

	private static final Logger logger =
			LoggerFactory.getLogger(UserService.class); // SLF4J를 활용한 로그 기록

	public void chkEmptyId(String id) {
		logger.info(">> 아이디 공란 여부 확인");

		if (id == null || id.isEmpty()) {
			throw EmptyIdException.baseCodeException; // 아이디가 공란인 경우
		}
	}

	public void chkLengthId(String id) {
		logger.info(">> 아이디 길이 확인");

		Pattern pattern = Pattern.compile(ID_PATTERN_LENGTH);

		if (!pattern.matcher(id).matches()) {
			throw IncorrectIdLengthException.baseCodeException; // 아이디 길이가 잘못된 경우
		}
	}

	public void chkPatternId(String id) {
		logger.info(">> 아이디 형식 확인");

		if (!Pattern.matches(ID_PATTERN, id)) {
			throw IncorrectIdException.baseCodeException; // 아이디 형식이 잘못된 경우
		}
	}

	public void chkAlreadyExistId(String id) {
		logger.info(">> 아이디 중복 여부 확인");

		userAdaptor.existsUserByUserAuth_Id(id);
	}

	public void chkEmptyNickname(String nickname) {
		logger.info(">> 닉네임 공란 여부 확인");

		if (nickname == null || nickname.isEmpty()) {
			throw EmptyNicknameException.baseCodeException; // 닉네임이 공란인 경우
		}
	}

	public void chkLengthNickname(String nickname) {
		logger.info(">> 닉네임 길이 확인");

		Pattern pattern = Pattern.compile(NICKNAME_PATTERN_LENGTH);

		if (!pattern.matcher(nickname).matches()) {
			throw IncorrectNicknameLengthException.baseCodeException; // 닉네임 길이가 잘못된 경우
		}
	}

	public void chkPatterNickname(String nickname) {
		logger.info(">> 닉네임 형식 확인");

		if (!Pattern.matches(NICKNAME_PATTERN, nickname)) {
			throw IncorrectNicknameException.baseCodeException; // 닉네임 형식이 잘못된 경우
		}
	}

	public void chkAlreadyExistNickname(String nickname) {
		logger.info(">> 닉네임 중복 여부 확인");

		userAdaptor.existsUserByUserProfile_Nickname(nickname);
	}

	public void chkEmptyEmail(String email) {
		logger.info(">> 이메일 공란 여부 확인");

		if (email == null || email.isEmpty()) {
			throw EmptyEmailException.baseCodeException; // 이메일이 공란인 경우
		}
	}

	public void chkPatternEmail(String email) {
		logger.info(">> 이메일 형식 확인");

		if (!Pattern.matches(EMAIL_PATTERN, email)) {
			throw IncorrectEmailException.baseCodeException; // 이메일 형식이 잘못된 경우
		}
	}

	public void chkAlreadyExistEmail(String email) {
		logger.info(">> 이메일 중복 여부 확인");

		userAdaptor.existsUserByUserProfile_Email(email);
	}

	public void register(
			String id, String password, String nickname, String email, Boolean receiveEmail) {
		logger.info(">> 회원가입");

		User user =
				User.builder()
						.userType(UserType.TYPE_NORMAL)
						.userState(UserState.STATE_NORMAL)
						.userAuth(UserAuth.builder().id(id).password(password).build())
						.userProfile(UserProfile.builder().nickname(nickname).email(email).build())
						.userToogle(UserToogle.builder().receiveEmail(receiveEmail).build())
						.build(); // 사용자 생성

		userAdaptor.save(user); // 사용자를 저장소에 저장
	}

	/*
	public LoginResponse login(LoginRequest req) { // 일반 로그인
		if (!userRepository.existsUserByUserAuth_Id(req.getId())) // 입력받은 아이디가 존재하지 않는 경우
			throw LoginFailureException.baseCodeException;

		User user =
				userRepository
						.findUserByUserAuth_Id(req.getId()) // 입력받은 아이디에 해당하는 회원 정보 불러옴
						.orElseThrow(LoginFailureException::new);

		if (!passwordEncoder.matches(
				req.getPassword(),
				user.getUserAuth().getPassword())) { // 입력받은 비밀번호와 저장된 비밀번호가 일치하지 않는 경우
			throw LoginFailureException.baseCodeException;
		}

		// AccessToken, refreshToken 생성
		String accessToken =
				jwtHandler.generateAccessToken(user.getIndex(), user.getUserType().getKey());
		String refreshToken = jwtHandler.generateRefreshToken(user.getIndex());

		// AccessToken, refreshToken 반환
		return LoginResponse.builder().accessToken(accessToken).refreshToken(refreshToken).build();
	}

	public User oauthRegister(){ // OAuth 회원가입

	}

	public User oauthLogin(){ // OAuth 로그인

	}

	public void logout(){ // 로그아웃

	}

	public void resign(){ // 회원탈퇴

	}
	*/
}
