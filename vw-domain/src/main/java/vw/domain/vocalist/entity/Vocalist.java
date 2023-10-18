package vw.domain.vocalist.entity;

import jakarta.persistence.*;
import java.sql.Date;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vw.domain.song.entity.SongVocalist;

@Getter
@Entity
@Table(name = "tbl_vocalist")
@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Vocalist { // 가수 엔티티
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vocalist_index", nullable = false, unique = true)
	private long index; // 가수 식별자

	@Column(name = "vocalist_original_name", nullable = false)
	private String originalName; // 가수 원래 이름

	@Column(name = "vocalist_translated_name")
	private String translatedName; // 가수 번역 이름

	@Column(name = "vocalist_release_date")
	private Date releaseDate; // 가수 투고일

	@OneToMany(mappedBy = "vocalist", cascade = CascadeType.PERSIST, orphanRemoval = true)
	private List<VocalistLink> links; // 가수 링크

	@OneToMany(mappedBy = "vocalist", cascade = CascadeType.PERSIST, orphanRemoval = true)
	private List<SongVocalist> vocalist; // 노래 가수

	@OneToMany(mappedBy = "vocalist", cascade = CascadeType.PERSIST, orphanRemoval = true)
	private List<VocalistLink> post; // 가수 포스트
}
