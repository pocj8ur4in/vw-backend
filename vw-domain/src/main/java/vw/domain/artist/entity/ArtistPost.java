package vw.domain.artist.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "tbl_artist_post")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArtistPost {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "artist_post_index", nullable = false, unique = true)
	private long index; // 아티스트 포스트 식별자

	@Lob
	@Column(name = "artist_post_content")
	private String content; // 아티스트 포스트 내용

	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Artist artist; // 아티스트 식별자
}
