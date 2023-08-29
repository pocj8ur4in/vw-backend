package vw.domain.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vw.domain.common.model.BaseDateTime;

@Getter
@Entity
@Table(name = "tbl_user")
@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseDateTime { // 회원 엔티티
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_index", nullable = false, unique = true)
	private long index; // 회원 식별자

	@Enumerated(EnumType.STRING)
	@Column(name = "user_type", nullable = false)
	private UserType userType; // 회원 유형 정보

	@Enumerated(EnumType.STRING)
	@Column(name = "user_state", nullable = false)
	private UserState userState; // 회원 상태 정보

	@Embedded private UserAuth userAuth; // 회원 인증 정보

	@Embedded private UserProfile userProfile; // 회원 프로필 정보

	@Embedded private UserToogle userToogle; // 회원 토글 정보

	@Builder
	public User(
			UserType userType,
			UserState userState,
			UserAuth userAuth,
			UserProfile userProfile,
			UserToogle userToogle) { // Builder 패턴으로 User 객체를 생성 가능하게 함
		this.userType = userType;
		this.userState = userState;
		this.userAuth = userAuth;
		this.userProfile = userProfile;
		this.userToogle = userToogle;
	}
}
