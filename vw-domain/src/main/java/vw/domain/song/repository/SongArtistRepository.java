package vw.domain.song.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import vw.domain.song.entity.SongArtist;

@EnableJpaRepositories
public interface SongArtistRepository extends JpaRepository<SongArtist, Long> { // 노래 아티스트 레포지토리
}
