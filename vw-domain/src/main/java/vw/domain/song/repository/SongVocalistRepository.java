package vw.domain.song.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import vw.domain.song.entity.SongVocalist;

@EnableJpaRepositories
public interface SongVocalistRepository extends JpaRepository<SongVocalist, Long> { // 노래 가수 레포지토리
}
