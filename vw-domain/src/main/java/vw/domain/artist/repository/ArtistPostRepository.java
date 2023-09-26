package vw.domain.artist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import vw.domain.artist.entity.ArtistPost;

@EnableJpaRepositories
public interface ArtistPostRepository extends JpaRepository<ArtistPost, Long> {}
