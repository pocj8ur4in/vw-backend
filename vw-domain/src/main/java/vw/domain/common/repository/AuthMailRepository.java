package vw.domain.common.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vw.domain.common.entity.AuthMail;

@Repository
public interface AuthMailRepository extends CrudRepository<AuthMail, String> { // 인증 이메일 레포지토리
	Optional<AuthMail> findAuthMailByEmail(String email); // 이메일로 인증 이메일 정보 탐색

	Optional<AuthMail> findAuthMailByAuthKey(String value); // 인증 키로 인증 이메일 정보 탐색

	Boolean existsAuthMailByEmail(String email); // 이메일이 존재하는지 확인
}
