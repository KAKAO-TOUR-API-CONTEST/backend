package com.example.aijeju.controller;

import com.example.aijeju.service.MyService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Controller
public class UserViewController {

    @Autowired
    private MyService myService;

    @GetMapping("/login")
    public String login(){
        return "oauthLogin";
    }

    @CrossOrigin(origins = "http://localhost:3000" ) // 컨트롤러에서 설정
    @GetMapping("/get-data")
    public Mono<String> getData() {
        return myService.getDataFromReactApp();
    }

    //회원가입.
    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }

    @GetMapping("logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        new SecurityContextLogoutHandler().logout(request,response,
                SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }
}
