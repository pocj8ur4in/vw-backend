package vw.domain.song.entity;

import jakarta.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Getter
@Entity
@Table(name = "tbl_song")
@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Song { // 노래 엔티티
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "song_index", nullable = false, unique = true)
	private long index; // 노래 식별자

	@Column(name = "song_original_name")
	private String originalName; // 노래 원래 이름

	@Column(name = "song_translated_name")
	private String translatedName; // 노래 번역 이름

	@Enumerated(EnumType.STRING)
	@Column(name = "song_type", nullable = false)
	private SongType type; // 노래 유형

	@Column(name = "song_duration")
	private Time duration; // 노래 길이

	@Column(name = "song_release_date")
	private Date releaseDate; // 노래 투고일

	@OneToMany(mappedBy = "song", cascade = CascadeType.PERSIST, orphanRemoval = true)
	private List<SongVocalist> vocalist; // 노래 가수

	@OneToMany(mappedBy = "song", cascade = CascadeType.PERSIST, orphanRemoval = true)
	private List<SongLink> link; // 노래 링크

	@OneToMany(mappedBy = "song", cascade = CascadeType.PERSIST, orphanRemoval = true)
	private List<SongArtist> artist; // 노래 아티스트

	@OneToMany(mappedBy = "song", cascade = CascadeType.PERSIST, orphanRemoval = true)
	private List<SongLyrics> lyrics; // 노래 가사

	@OneToMany(mappedBy = "song", cascade = CascadeType.PERSIST, orphanRemoval = true)
	private List<SongPost> post; // 노래 포스트

	// @Enumerated(EnumType.STRING)
	// @Column(name = "song_record")
	// private List<SongRecord> record; // 노래 달성 기록

	// @Column(name = "song_derived")
	// private String derived; // 노래 상위 항목
}
