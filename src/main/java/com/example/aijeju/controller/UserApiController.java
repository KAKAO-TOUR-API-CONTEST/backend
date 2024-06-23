package com.example.aijeju.controller;

import com.example.aijeju.domain.User;
import com.example.aijeju.dto.AddUserRequest;
import com.example.aijeju.dto.CreateAccessTokenResponse;
import com.example.aijeju.dto.LoginRequest;
import com.example.aijeju.jwt.TokenProvider;
import com.example.aijeju.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.example.aijeju.service.UserService;
import org.springframework.web.reactive.result.view.RedirectView;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;


    private  final UserRepository userRepository;
    private final TokenProvider tokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //회원가입
    @CrossOrigin(origins="http://localhost:3000")
    @PostMapping("/user")
    public String signup(@RequestBody AddUserRequest request){
        userService.save(request);
        return "sign up success";
    }

    /*
    //로그인
    @CrossOrigin(origins="http://localhost:3000")
    @PostMapping("/login")
    public ResponseEntity<CreateAccessTokenResponse> login(@RequestBody LoginRequest request, HttpServletResponse response) {
        boolean isAuthenticated = userService.login(request.getEmail(), request.getPassword());
        if (isAuthenticated) {
            User user = userRepository.findByEmail(request.getEmail()).get();
            String accessToken = tokenProvider.generateToken(user, Duration.ofDays(1));

            String refreshToken = tokenProvider.generateToken(user, Duration.ofDays(14));

            Cookie cookie = new Cookie("accessToken", accessToken);
            cookie.setHttpOnly(true);
            cookie.setMaxAge(86400); // 1 day in seconds
            cookie.setPath("/"); // 쿠키를 모든 경로에서 유효하게 설정
            response.addCookie(cookie);

            return ResponseEntity.ok().build();

        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }*/

    //로그인 성공시 이동하는 페이지
    @CrossOrigin(origins="http://localhost:3000")
    @GetMapping("/main")
    public void exRedirect3(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.sendRedirect("http://localhost:3000/api/main");
    }




}
