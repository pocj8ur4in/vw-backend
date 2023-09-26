package vw.domain.vocalist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import vw.domain.vocalist.entity.Vocalist;

@EnableJpaRepositories
public interface VocalistRepository extends JpaRepository<Vocalist, Long> {}
