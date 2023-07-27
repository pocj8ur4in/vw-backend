package vw.domain.user.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import vw.domain.user.entity.User;
import vw.domain.user.entity.UserAuth;
import vw.domain.user.entity.UserProfile;
import vw.domain.user.entity.UserToogle;
import vw.domain.user.exception.UserNotFoundException;

@DataJpaTest // 테스트용 데이터베이스를 설정하고, JPA 리포지토리를 테스트하기 위해 필요한 컴포넌트들을 구성
public class UserRepositoryTest {
    @Autowired UserRepository userRepository;

    // EntityManager : 영속성 컨텍스트를 생성하고 관리. 엔티티의 CRUD 작업 수행
    @PersistenceContext EntityManager entityManager;

    @Test
    void createAndReadTest() { // 회원 저장소 내의 회원 정보 생성 및 조회
        // given
        User givenUser = createUser();

        // when
        userRepository.save(givenUser);
        clear();

        // then
        User thenUser =
                userRepository
                        .findUserByUserAuth_Id(givenUser.getUserAuth().getId())
                        .orElseThrow(UserNotFoundException::new);

        // assertThat : 주어진 값이 기대한 값과 일치하는지 확인
        assertThat(thenUser.getUserAuth().getPassword())
                .isEqualTo(givenUser.getUserAuth().getPassword());
    }

    @Test
    void updateTest() { // 회원 저장소 내의 회원 정보 갱신
        // given
        String newPassword = "updated";

        User givenUser = userRepository.save(createUser());
        clear();

        // when
        User whenUser =
                userRepository
                        .findUserByUserAuth_Id(givenUser.getUserAuth().getId())
                        .orElseThrow(UserNotFoundException::new);

        whenUser.getUserAuth().setPassword(newPassword);
        clear();

        // then
        User thenUser =
                userRepository
                        .findUserByUserAuth_Id(givenUser.getUserAuth().getId())
                        .orElseThrow(UserNotFoundException::new);

        assertThat(thenUser.getUserAuth().getPassword()).isEqualTo(newPassword);
    }

    @Test
    void deleteTest() { // 회원 저장소 내의 회원 정보 삭제
        // given
        User givenUser = userRepository.save(createUser());
        clear();

        // when
        userRepository.delete(givenUser);

        // then
        // assertThatThrownBy :  특정 코드 블록이 예상한 예외를 던지는지 확인
        assertThatThrownBy(
                        () ->
                                userRepository
                                        .findUserByUserAuth_Id(givenUser.getUserAuth().getId())
                                        .orElseThrow(UserNotFoundException::new))
                .isInstanceOf(UserNotFoundException.class);
    }

    @Test
    void findByNicknameTest() { // 주어진 닉네임으로 회원 저장소 내의 회원 정보 조회
        // given
        User givenUser = userRepository.save(createUser());
        clear();

        // when
        User whenUser =
                userRepository
                        .findUserByUserProfile_Nickname(givenUser.getUserProfile().getNickname())
                        .orElseThrow(UserNotFoundException::new);

        // then
        assertThat(whenUser.getUserAuth().getPassword())
                .isEqualTo(givenUser.getUserAuth().getPassword());
    }

    @Test
    void findByEmailTest() { // 주어진 이메일로 회원 저장소 내의 회원 정보 조회
        // given
        User givenUser = userRepository.save(createUser());
        clear();

        // when
        User whenUser =
                userRepository
                        .findUserByUserProfile_Email(givenUser.getUserProfile().getEmail())
                        .orElseThrow(UserNotFoundException::new);

        // then
        assertThat(whenUser.getUserAuth().getPassword())
                .isEqualTo(givenUser.getUserAuth().getPassword());
    }

    private void clear() {
        entityManager.flush(); // 쿼리를 즉시 수행
        entityManager.clear(); // 캐시를 비움
    }

    private User createUser() {
        return new User(
                new UserAuth("id", "password"),
                new UserProfile("nickname", "email", "image"),
                new UserToogle(Boolean.TRUE));
    }
}
