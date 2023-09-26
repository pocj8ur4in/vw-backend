package vw.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import vw.domain.user.entity.UserPost;

@EnableJpaRepositories
public interface UserPostRepository extends JpaRepository<UserPost, Long> {}
