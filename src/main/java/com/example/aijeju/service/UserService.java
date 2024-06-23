package com.example.aijeju.service;

import com.example.aijeju.domain.User;
import com.example.aijeju.dto.AddUserRequest;
import com.example.aijeju.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.aijeju.repository.UserRepository;

import java.time.Duration;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    public Long save(AddUserRequest dto){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return userRepository.save(User.builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .password(encoder.encode(dto.getPassword()))
                .build()).getId();
    }

    /*
    public boolean login(String email, String rawPassword) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            //String accessToken = tokenProvider.generateToken(user, Duration.ofDays(1));
            //String refreshToken = tokenProvider.generateToken(user, Duration.ofDays(14));

            // 입력된 평문 비밀번호와 저장된 해시된 비밀번호 비교
            return bCryptPasswordEncoder.matches(rawPassword, user.getPassword());
        }
        return false;
    }*/


    //리프레시 토큰을 전달받아 검증하고, 유효한 리프레시 토큰이라면 새로운 엑세스 토큰을 생성하는 토큰 API를 구현한다.
    public User findById(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }

    //이메일을 입력받아 users 테이블에서 유저를 찾는다
    public User findByEmail(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(
                        ()-> new IllegalArgumentException("unexpected user"));
    }


}
