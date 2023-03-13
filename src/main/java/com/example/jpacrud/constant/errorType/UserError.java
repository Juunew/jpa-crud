package com.example.jpacrud.constant.errorType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum UserError {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "회원을 찾을 수 없습니다."),
    UNAUTHORIZED_USER(HttpStatus.FORBIDDEN, "권한이 없는 회원입니다."),
    ALREADY_LIKED(HttpStatus.CONFLICT, "이미 좋아요를 한 게시물입니다.");

    private final HttpStatus status;
    private final String message;
}
