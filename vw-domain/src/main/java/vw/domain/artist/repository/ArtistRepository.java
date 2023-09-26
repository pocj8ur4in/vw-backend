package vw.domain.artist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import vw.domain.artist.entity.Artist;

@EnableJpaRepositories
public interface ArtistRepository extends JpaRepository<Artist, Long> {}
