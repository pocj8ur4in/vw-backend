package vw.domain.artist.entity;

import jakarta.persistence.*;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import vw.domain.common.model.LinkCategory;
import vw.domain.common.model.LinkType;

@Entity
@Table(name = "tbl_artist_link")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArtistLink { // 아티스트 링크 엔티티
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "artist_link_index", nullable = false, unique = true)
	private long index; // 아티스트 링크 식별자

	@Enumerated(EnumType.STRING)
	@Column(name = "artist_link_category", nullable = false)
	private LinkCategory category; // 아티스트 링크 카테고리

	@Enumerated(EnumType.STRING)
	@Column(name = "artist_link_type", nullable = false)
	private LinkType type; // 아티스트 링크 유형

	@Column(name = "artist_link_description")
	private String description; // 아티스트 링크 설명

	@Lob
	@Column(name = "artist_link_content", nullable = false)
	private String content; // 아티스트 링크 내용

	@Column(name = "artist_is_deleted", nullable = false)
	private Boolean isDeleted; // 아티스트 링크 삭제 여부

	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Artist artist; // 아티스트 식별자

	@OneToMany(mappedBy = "artistLink", cascade = CascadeType.PERSIST, orphanRemoval = true)
	private List<ArtistLinkInfo> linkInfo; // 아티스트 링크 정보
}
