package com.example.aijeju.config;

import com.example.aijeju.jwt.TokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


//이 필터는 엑세스 토큰값이 담긴 Authorization 헤더값을 가져온 뒤 엑세스 토큰이 유효하다면 인증정보를 설정한다.
@RequiredArgsConstructor
public class TokenAuthenticationFilter  extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;
    private final static String HEADER_AUTHORIZATION = "Authorization";
    private final static String TOKEN_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException{

        //요청 헤더의 Authorization 키의 값 조회한다.
        String authorizationHeader = request.getHeader(HEADER_AUTHORIZATION);
        // 가져온 값에서 접두사 제거
        String token = getAccesToken(authorizationHeader);
        // 가져온 토큰이 유효한지 확인하고, 유효한 때는 인증정보 설정한다.
        if(tokenProvider.validToken(token)){
            Authentication authentication = tokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request,response);
    }

    private String getAccesToken(String authorizationHeader){
        System.out.println("get Access Token 실행");
        if(authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)){
            return authorizationHeader.substring(TOKEN_PREFIX.length());
        }
        return null;
    }

}
