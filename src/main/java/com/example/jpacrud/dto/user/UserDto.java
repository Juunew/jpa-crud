package com.example.jpacrud.dto.user;

import com.example.jpacrud.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserDto {

    private Long userId;
    private String nickname;

    public static UserDto fromEntity(UserEntity user) {
        String nickname = user.getNickname() + "(" + user.getAccountType().getUserRole() + ")";
        return new UserDto(user.getId(), nickname);
    }

}
