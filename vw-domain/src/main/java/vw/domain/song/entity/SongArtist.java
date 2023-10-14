package vw.domain.song.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import vw.domain.artist.entity.Artist;
import vw.domain.artist.entity.ArtistType;

@Entity
@Table(name = "tbl_song_artist")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SongArtist {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "song_artist_index", nullable = false, unique = true)
	private long index; // 노래 아티스트 식별자

	@Enumerated(EnumType.STRING)
	@Column(name = "song_artist_type", nullable = false)
	private ArtistType type; // 노래 아티스트 타입

	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Song song; // 노래 식별자

	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Artist artist; // 아티스트 식별자
}
