package com.example.date_scheduling.user.dto;

import com.example.date_scheduling.user.entity.UserEntity;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private String email;
    private String nickName;
    private String loginId;
    private String token;

    public UserResponseDTO(UserEntity user){
        this.email = user.getEmail();
        this.nickName = user.getNickName();
        this.loginId = user.getLoginId();
    }
}
