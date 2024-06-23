package com.example.aijeju.domain;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name="users")
@NoArgsConstructor(access= AccessLevel.PROTECTED) //기본생성자
@Getter
@Entity
@AllArgsConstructor // 모든 필드를 초기화하는 생성자
@Builder // 빌더 패턴
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name="email", nullable = false, unique = true)
    private String email;

    @Column(name = "password")
    private String password;


    //nickname?
    @Column(name = "name")
    private String name;

    /*
    @Builder
    public User(String name, String password, String auth){
        this.name = name;
        this.password = password;
    }*/

    @Builder
    public User(String email, String password, String name){
        this.email = email;
        this.password = password;
        this.name = name;
    }

    //사용자 이름 변경
    public User update(String name){
        this.name = name;
        return this;
    }

    // 사용자가 가지고 있는 권한의 목록을 반환한다.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }
    //사용자를 식별할 수 있는 사용자 이름을 반환한다.
    @Override
    public String getUsername() {
        return email;
    }
    //사용자의 비밀번호를 반환한다. 이 때 저장되어 있는 비밀번호는 암호화해서 저장해야 한다.
    @Override
    public String getPassword(){
        return password;
    }
    //계정이 만료되었는지 확인하는 메서드, 만료되지 않았을때 true 반환
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    //계정이 잠금되었는지 확인하는 메서드, 잠금되지 않았을 때 true 반환
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    //비밀번호가 만료되었는지 확인하는 메서드, 만료되지 않았을 때 true 반환
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    //계정이 사용 가능한지 확인하는 메서드, 사용가능하다면 true 반환
    @Override
    public boolean isEnabled() {
        return true;
    }
}
