package vw.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vw.core.dto.user.*;
import vw.core.handler.JwtHandler;
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
    private JwtHandler jwtHandler;

    public void validateId(ValidateIdRequest req) { // 아이디 검증
        validateId(req.getId());
    }

    public void validateNickname(ValidateNicknameRequest req) { // 닉네임 검증
        validateNickname(req.getNickname());
    }

    public void validateEmail(ValidateEmailRequest req) { // 이메일 검증
        validateEmail(req.getEmail());
    }

    /*
       // 비밀번호 검증 : 암호화된 정보를 전송받을 것이므로, 다음 사항들은 Controller 단에서 검증
       - 영문, 특수문자, 숫자를 모두 포함하여 8자리 이상
       - 동일한 문자를 4자리 이상 연속해서 사용하면 안됨
       - 아이디를 포함하면 안됨
       - 특수문자는 !@#$^*+=-만 가능
       - 연속된 문자열을 사용하면 안됨
    */

    public void register(RegisterRequest req) { // 일반 회원가입
        validateId(req.getId()); // 아이디 검증
        validateNickname(req.getNickname()); // 닉네임 검증
        validateEmail(req.getEmail()); // 이메일 검증
        // 비밀번호 검증 : 위에 동일하게 Controller 단에서 검증

        User user =
                User.builder()
                        .userAuth(
                                UserAuth.builder()
                                        .id(req.getId())
                                        .password(req.getPassword())
                                        .build())
                        .userProfile(
                                UserProfile.builder()
                                        .nickname(req.getNickname())
                                        .email(req.getEmail())
                                        .build())
                        .userToogle(
                                UserToogle.builder().receiveEmail(req.getReceiveEmail()).build())
                        .build(); // 사용자 생성

        userRepository.save(user); // 사용자를 저장소에 저장

        if (!userRepository.existsUserByUserAuth_Id(user.getUserAuth().getId()))
            throw RegisterFailureException.baseCodeException; // 회원가입이 실패한 경우
    }

    public LoginResponse login(LoginRequest req) { // 일반 로그인
        if (!userRepository.existsUserByUserAuth_Id(req.getId())) // 입력받은 아이디가 존재하지 않는 경우
        throw LoginFailureException.baseCodeException;

        User user =
                userRepository
                        .findUserByUserAuth_Id(req.getId()) // 입력받은 아이디에 해당하는 회원 정보 불러옴
                        .orElseThrow(LoginFailureException::new);

        if (!passwordEncoder.matches(
                req.getPassword(),
                user.getUserAuth().getPassword())) { // 입력받은 비밀번호와 저장된 비밀번호가 일치하지 않는 경우
            throw LoginFailureException.baseCodeException;
        }

        String accessToken =
                jwtHandler.generateAccessToken(user.getIndex(), user.getUserType().getValue());
        String refreshToken = jwtHandler.generateRefreshToken(user.getIndex());

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build(); // AccessToken, refreshToken 반환
    }

    /*
       public User oauthRegister(){ // OAuth 회원가입

       }

       public User oauthLogin(){ // OAuth 로그인

       }

       public void logout(){ // 로그아웃

       }

       public void resign(){ // 회원탈퇴

       }
    */

    private void validateId(String Id) { // 아이디 검증
        if (Id.length() > 15) throw TooLongIdException.baseCodeException; // 아이디 길이가 너무 긴 경우
        else if (!userRepository.existsUserByUserAuth_Id(Id))
            throw AlreadyExistIdException.baseCodeException; // 이미 아이디가 존재하는 경우
    }

    private void validateNickname(String nickname) { // 닉네임 검증
        if (nickname.length() > 8)
            throw TooLongNicknameException.baseCodeException; // 닉네임 길이가 너무 긴 경우
        else if (!userRepository.existsUserByUserAuth_Id(nickname))
            throw AlreadyExistNicknameException.baseCodeException; // 이미 닉네임이 존재하는 경우
    }

    private void validateEmail(String email) { // 이메일 검증
        if (!userRepository.existsUserByUserProfile_Email(email))
            throw AlreadyExistEmailException.baseCodeException; // 이미 이메일이 존재하는 경우
    }
}
