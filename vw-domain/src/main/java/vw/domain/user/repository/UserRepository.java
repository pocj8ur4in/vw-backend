package vw.domain.user.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import vw.domain.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findUserByUserAuth_Id(String id); // 아이디로 회원을 조회

	Optional<User> findUserByUserProfile_Nickname(String nickname); // 닉네임으로 회원을 조회

	Optional<User> findUserByUserProfile_Email(String email); // 이메일로 회원을 조회

	Boolean existsUserByUserAuth_Id(String id); // 아이디로 회원 존재 여부를 조회

	Boolean existsUserByUserProfile_Email(String email); // 이메일로 회원 존재 여부를 조회
}
