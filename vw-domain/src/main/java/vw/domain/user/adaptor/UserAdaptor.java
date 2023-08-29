package vw.domain.user.adaptor;

import lombok.RequiredArgsConstructor;
import vw.core.annotation.Adaptor;
import vw.core.exception.user.*;
import vw.domain.user.entity.User;
import vw.domain.user.repository.UserRepository;

@Adaptor
@RequiredArgsConstructor
public class UserAdaptor {
	private final UserRepository userRepository;

	public void save(User user) {
		try {
			userRepository.save(user);
			notExistsUserByUserAuth_Id(user.getUserAuth().getId());
		} catch (Exception exception) {
			throw RegisterFailureException.baseCodeException; // 회원가입이 실패한 경우
		}
	}

	public void existsUserByUserAuth_Id(String id) {
		if (userRepository.existsUserByUserAuth_Id(id))
			throw AlreadyExistIdException.baseCodeException; // 이미 아이디가 존재하는 경우
	}

	public void existsUserByUserProfile_Nickname(String nickname) {
		if (userRepository.existsUserByUserProfile_Nickname(nickname))
			throw AlreadyExistNicknameException.baseCodeException; // 이미 닉네임이 존재하는 경우
	}

	public void existsUserByUserProfile_Email(String email) {
		if (userRepository.existsUserByUserProfile_Email(email))
			throw AlreadyExistEmailException.baseCodeException; // 이미 이메일이 존재하는 경우
	}

	public void notExistsUserByUserAuth_Id(String id) {
		if (!userRepository.existsUserByUserAuth_Id(id))
			throw RegisterFailureException.baseCodeException; // 아이디가 존재하지 않는 경우
	}

	public User findUserById(Long id) {
		return userRepository
				.findById(id)
				.orElseThrow(() -> UserNotFoundException.baseCodeException);
	}

	public User findUserByUserAuth_Id(String id) {
		return userRepository
				.findUserByUserAuth_Id(id)
				.orElseThrow(() -> UserNotFoundException.baseCodeException);
	}

	public User findUserByUserProfile_Nickname(String nickname) {
		return userRepository
				.findUserByUserProfile_Nickname(nickname)
				.orElseThrow(() -> UserNotFoundException.baseCodeException);
	}

	public User findUserByUserProfile_Email(String email) {
		return userRepository
				.findUserByUserProfile_Email(email)
				.orElseThrow(() -> UserNotFoundException.baseCodeException);
	}
}
