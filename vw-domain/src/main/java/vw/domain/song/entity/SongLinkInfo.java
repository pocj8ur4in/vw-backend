package vw.domain.song.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "tbl_song_link_info")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SongLinkInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "song_link_info_index", nullable = false, unique = true)
	private long index; // 노래 링크 정보 식별자

	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private SongLink songLink; // 노래 링크 식별자
}
