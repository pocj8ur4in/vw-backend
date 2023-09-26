package vw.domain.song.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import vw.domain.vocalist.entity.Vocalist;

@Entity
@Table(name = "tbl_song_vocalist")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SongVocalist {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "song_vocalist_index", nullable = false, unique = true)
	private long index; // 노래 가수 식별자

	@Column(name = "song_vocalist_is_chorus", nullable = false)
	private Boolean isChorus; // 노래 가수 코러스 여부

	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Song song; // 노래 식별자

	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Vocalist vocalist; // 가수 식별자
}
