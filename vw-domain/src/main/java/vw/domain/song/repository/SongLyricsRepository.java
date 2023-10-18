package vw.domain.song.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import vw.domain.song.entity.SongLyrics;

@EnableJpaRepositories
public interface SongLyricsRepository extends JpaRepository<SongLyrics, Long> { // 노래 가사 레포지토리
}
