package vw.domain.vocalist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import vw.domain.vocalist.entity.VocalistPost;

@EnableJpaRepositories
public interface VocalistPostRepository extends JpaRepository<VocalistPost, Long> { // 가수 포스트 레포지토리
}
