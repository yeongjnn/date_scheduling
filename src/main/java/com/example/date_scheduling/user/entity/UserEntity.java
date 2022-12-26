package com.example.date_scheduling.user.entity;

import com.example.date_scheduling.user.dto.UserRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class UserEntity {
    private String id; //회원 아이디 식별하는 아이디. 컴퓨터가 알아서 정해주는 무작위 String
    private String nickName; // 회원의 닉네임 (중복 불가능)
                             // 게시물의 userId와 같은 값
    private String loginId; //회원가입시 입력하는 id (중복 불가능)
    private String email;   // 중복 불가능
    private String password;    // 비밀번호 (데이터베이스에서 보이지 않게)

    public UserEntity(){
        this.id = UUID.randomUUID().toString();
    }

    public UserEntity(UserRequestDTO dto){
        this();
        this.nickName = dto.getNickName();
        this.loginId = dto.getLoginId();
        this.email = dto.getEmail();
        this.password = dto.getPassword();
    }
}
