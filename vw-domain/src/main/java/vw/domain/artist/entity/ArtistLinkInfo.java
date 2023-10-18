package vw.domain.artist.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "tbl_artist_link_info")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArtistLinkInfo { // 아티스트 링크 정보 엔티티
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "artist_link_info_index", nullable = false, unique = true)
	private long index; // 아티스트 링크 정보 식별자

	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private ArtistLink artistLink; // 아티스트 링크 식별자
}
