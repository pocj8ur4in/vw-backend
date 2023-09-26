package vw.domain.artist.entity;

import jakarta.persistence.*;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vw.domain.song.entity.SongArtist;

@Getter
@Entity
@Table(name = "tbl_artist")
@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Artist { // 아티스트 엔티티
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "artist_index", nullable = false, unique = true)
	private long index; // 아티스트 식별자

	@Column(name = "artist_original_name", nullable = false)
	private String originalName; // 아티스트 원래 이름

	@Column(name = "artist_translated_name")
	private String translatedName; // 아티스트 번역 이름

	@OneToMany(mappedBy = "artist", cascade = CascadeType.PERSIST, orphanRemoval = true)
	private List<SongArtist> song; // 아티스트 노래

	@OneToMany(mappedBy = "artist", cascade = CascadeType.PERSIST, orphanRemoval = true)
	private List<ArtistLink> link; // 아티스트 링크

	@OneToMany(mappedBy = "artist", cascade = CascadeType.PERSIST, orphanRemoval = true)
	private List<ArtistPost> post; // 아티스트 포스트
}
