package vw.domain.common.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vw.domain.common.entity.AuthMail;

@Repository
public interface AuthMailRepository extends CrudRepository<AuthMail, String> {
	Optional<AuthMail> findAuthMailByEmail(String email);

	Optional<AuthMail> findAuthMailByAuthKey(String value);

	Boolean existsAuthMailByEmail(String email);
}
