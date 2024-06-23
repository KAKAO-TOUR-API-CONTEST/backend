package com.example.aijeju.service;

import com.example.aijeju.domain.User;
import com.example.aijeju.jwt.TokenProvider;
import com.example.aijeju.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class TokenService {

    private  final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    //전달받은 리프레시 토큰으로 토큰 유효성 검사를 진행하고, 유효한 토큰일 때 리프레시 토큰으로 사용자 id 를 찾는다.
    public String createNewAccessToken(String refreshToken){

        //토큰 유효성 검사에 실패하면 예외 발생
        if(!tokenProvider.validToken(refreshToken)){

            if(!tokenProvider.validToken(refreshToken)){
                throw new IllegalArgumentException("Unexpected token");
            }
        }

        Long userId = refreshTokenService.findByRefreshToken(refreshToken).getUserId();
        User user = userService.findById(userId);

        //찾은 사용자 id로 새로운 access token을 생성한다.
        return tokenProvider.generateToken(user, Duration.ofDays(2));
    }
}
