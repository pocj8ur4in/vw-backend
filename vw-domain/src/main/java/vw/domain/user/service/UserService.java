package vw.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vw.domain.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
}
