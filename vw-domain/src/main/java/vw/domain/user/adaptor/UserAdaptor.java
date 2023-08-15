package vw.domain.user.adaptor;

import lombok.RequiredArgsConstructor;
import vw.domain.common.annotation.Adaptor;
import vw.domain.user.entity.User;
import vw.domain.user.exception.*;
import vw.domain.user.repository.UserRepository;

@Adaptor
@RequiredArgsConstructor
public class UserAdaptor {
	private final UserRepository userRepository;

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
