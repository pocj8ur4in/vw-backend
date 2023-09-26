package vw.domain.song.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import vw.domain.song.entity.Song;

@EnableJpaRepositories
public interface SongRepository extends JpaRepository<Song, Long> {}
