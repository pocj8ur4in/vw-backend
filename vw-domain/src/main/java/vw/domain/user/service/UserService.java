package vw.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vw.domain.user.adaptor.UserAdaptor;
import vw.domain.user.entity.User;
import vw.domain.user.entity.UserAuth;
import vw.domain.user.entity.UserProfile;
import vw.domain.user.entity.UserToogle;
import vw.domain.user.exception.*;
import vw.domain.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserAdaptor userAdaptor;
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public void validateId(String id) { // 아이디 검증
        if (id.length() > 15) throw TooLongIdException.baseCodeException; // 아이디 길이가 너무 긴 경우
        else if (!userRepository.existsUserByUserAuth_Id(id))
            throw AlreadyExistIdException.baseCodeException; // 이미 아이디가 존재하는 경우
    }

    public void validateNickname(String nickname) { // 닉네임 검증
        if (nickname.length() > 8)
            throw TooLongNicknameException.baseCodeException; // 닉네임 길이가 너무 긴 경우
        else if (!userRepository.existsUserByUserAuth_Id(nickname))
            throw AlreadyExistNicknameException.baseCodeException; // 이미 닉네임이 존재하는 경우
    }

    public void validateEmail(String email) { // 이메일 검증
        if (!userRepository.existsUserByUserProfile_Email(email))
            throw AlreadyExistEmailException.baseCodeException; // 이미 이메일이 존재하는 경우
    }

    /*
       // 비밀번호 검증 : 암호화된 정보를 전송받을 것이므로, 이래 사항들은 Controller 단에서 검증
       - 영문, 특수문자, 숫자를 모두 포함하여 8자리 이상
       - 동일한 문자를 4자리 이상 연속해서 사용하면 안됨
       - 아이디를 포함하면 안됨
       - 특수문자는 !@#$^*+=-만 가능
       - 연속된 문자열을 사용하면 안됨
    */

    public User register(
            UserAuth userAuth, UserProfile userProfile, UserToogle userToogle) { // 일반 회원가입
        validateId(userAuth.getId()); // 아이디 검증
        validateNickname(userProfile.getNickname()); // 닉네임 검증
        validateEmail(userProfile.getEmail()); // 이메일 검증
        // 비밀번호 검증 : 위에 동일하게 Controller 단에서 검증

        User user =
                User.builder()
                        .userAuth(
                                new UserAuth(
                                        userAuth.getId(),
                                        passwordEncoder.encode(userAuth.getPassword())))
                        .userProfile(userProfile)
                        .userToogle(userToogle)
                        .build(); // 사용자 생성

        userRepository.save(user); // 사용자를 저장소에 저장

        if (!userRepository.existsUserByUserAuth_Id(user.getUserAuth().getId()))
            throw UserNotRegisteredException.baseCodeException; // 회원가입이 실패한 경우
        else return user;
    }

    /*
       public User login(){ // 일반 로그인

       }

       public User oauthRegister(){ // OAuth 회원가입

       }

       public User oauthLogin(){ // OAuth 로그인

       }

       public void logout(){ // 로그아웃

       }

       public void resign(){ // 회원탈퇴

       }
    */
}
