package vw.domain.song.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "tbl_song_lyrics")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SongLyrics { // 노래 가사 엔티티
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "song_lyrics_index", nullable = false, unique = true)
	private long index; // 노래 가사 식별자

	@Lob
	@Column(name = "song_lyrics_content", nullable = false)
	private String content; // 노래 가사 내용

	@Enumerated(EnumType.STRING)
	@Column(name = "song_lyrics_language", nullable = false)
	private SongLyricsLanguage songLyricsLanguage; // 노래 가사 언어

	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Song song; // 노래 식별자
}
