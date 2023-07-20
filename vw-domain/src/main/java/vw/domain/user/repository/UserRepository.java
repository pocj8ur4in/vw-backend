package vw.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vw.domain.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {}
