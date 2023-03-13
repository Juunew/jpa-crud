package com.example.jpacrud.dto.like;

import com.example.jpacrud.dto.user.UserDto;
import com.example.jpacrud.entity.LikeEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class LikeDto {

    private Long userId;
    private String nickname;

    public static LikeDto fromEntity(LikeEntity like) {
        return new LikeDto(
                UserDto.fromEntity(like.getUser()).getUserId(),
                UserDto.fromEntity(like.getUser()).getNickname()
        );
    }

}
