package com.example.aijeju.service;


import com.example.aijeju.domain.RefreshToken;
import com.example.aijeju.domain.User;
import com.example.aijeju.dto.AddUserRequest;
import com.example.aijeju.dto.CreateAccessTokenRequest;
import com.example.aijeju.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;




    //전달받은 리프레시 토큰으로 리프레시 토큰 객체를 검색해서 전달하는 findByRefresh Token() 메서드를 구현한다.
    public RefreshToken findByRefreshToken(String refreshToken){
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected token"));
    }
}
