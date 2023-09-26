package vw.domain.song.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "tbl_song_post")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SongPost {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "song_post_index", nullable = false, unique = true)
	private long index; // 노래 포스트 식별자

	@Lob
	@Column(name = "song_post_content")
	private String content; // 노래 포스트 내용

	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Song song; // 노래 식별자
}
