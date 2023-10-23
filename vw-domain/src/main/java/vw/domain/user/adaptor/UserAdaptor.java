package vw.domain.user.adaptor;

import lombok.RequiredArgsConstructor;
import vw.core.annotation.Adaptor;
import vw.core.exception.user.*;
import vw.domain.user.entity.User;
import vw.domain.user.repository.UserRepository;

@Adaptor
@RequiredArgsConstructor
public class UserAdaptor { // 회원 레포지토리 어댑터
	private final UserRepository userRepository;

	public void existsUserByUserAuth_Id(String id) { // 아이디가 존재하는지 확인
		if (userRepository.existsUserByUserAuth_Id(id))
			throw AlreadyExistIdException.baseCodeException; // 이미 아이디가 존재하는 경우
	}

	public void existsUserByUserProfile_Nickname(String nickname) { // 닉네임이 존재하는지 확인
		if (userRepository.existsUserByUserProfile_Nickname(nickname))
			throw AlreadyExistNicknameException.baseCodeException; // 이미 닉네임이 존재하는 경우
	}

	public void existsUserByUserProfile_Email(String email) { // 이메일이 존재하는지 확인
		if (userRepository.existsUserByUserProfile_Email(email))
			throw AlreadyExistEmailException.baseCodeException; // 이미 이메일이 존재하는 경우
	}

	public void save(User user) { // 회원가입 (회원 정보 저장)
		try {
			userRepository.save(user);
			if (!userRepository.existsUserByUserAuth_Id(user.getUserAuth().getId()))
				throw new RuntimeException(); // 아이디가 존재하지 않는 경우
		} catch (Exception exception) {
			throw RegisterFailureException.baseCodeException; // 회원가입이 실패한 경우
		}
	}
}
