package vw.domain.song.entity;

import jakarta.persistence.*;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import vw.domain.common.entity.LinkCategory;
import vw.domain.common.entity.LinkType;

@Entity
@Table(name = "tbl_song_link")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SongLink {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "song_link_index", nullable = false, unique = true)
	private long index; // 노래 링크 식별자

	@Enumerated(EnumType.STRING)
	@Column(name = "song_link_type", nullable = false)
	private LinkType type; // 노래 링크 유형

	@Enumerated(EnumType.STRING)
	@Column(name = "song_link_category", nullable = false)
	private LinkCategory category; // 노래 링크 카테고리

	@Column(name = "song_link_description")
	private String description; // 노래 링크 설명

	@Column(name = "song_link_content", nullable = false, length = 1023)
	private String content; // 노래 링크 내용

	@Column(name = "song_link_is_deleted", nullable = false)
	private Boolean isDeleted; // 노래 링크 삭제 여부

	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Song song; // 노래 식별자

	@OneToMany(mappedBy = "songLink", cascade = CascadeType.PERSIST, orphanRemoval = true)
	private List<SongLinkInfo> linkInfo; // 노래 링크 정보
}
