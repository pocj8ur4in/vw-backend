package vw.domain.song.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import vw.domain.song.entity.SongPost;

@EnableJpaRepositories
public interface SongPostRepository extends JpaRepository<SongPost, Long> { // 노래 포스트 레포지토리
}
