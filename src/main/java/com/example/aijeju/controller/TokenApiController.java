package com.example.aijeju.controller;

import com.example.aijeju.dto.CreateAccessTokenRequest;
import com.example.aijeju.dto.CreateAccessTokenResponse;
import com.example.aijeju.service.TokenService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class TokenApiController {
    private final TokenService tokenService;

    //리프레시 토큰을 기반으로 액세스 토큰을 만들어준다.
    @PostMapping("/api/token")
    public ResponseEntity<CreateAccessTokenResponse> createNewAccessToken
            (@RequestBody CreateAccessTokenRequest request){
        System.out.println("리프레시 토큰을 기반으로 액세스 토큰 생성");
        String newAccessToken = tokenService.createNewAccessToken(request.getRefreshToken());

    return ResponseEntity.status(HttpStatus.CREATED)
            .body(new CreateAccessTokenResponse(newAccessToken));
    }

    @PostMapping("/main")
    public void exRedirect3(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.sendRedirect("https://www.naver.com");
    }
}
