package com.example.jpacrud.dto.post;

import com.example.jpacrud.dto.user.UserDto;
import com.example.jpacrud.entity.PostEntity;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostDto {

    private Long postId;
    private String title;
    private String content;
    private UserDto writerInfo;
    private int likeTotalCount;
    private boolean isLike;
    private LocalDateTime registeredAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    private PostDto(Long postId, String title, String content, UserDto userDto, LocalDateTime registeredAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.writerInfo = userDto;
        this.registeredAt = registeredAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public static PostDto fromEntity(PostEntity post) {
        return new PostDto(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                UserDto.fromEntity(post.getUser()),
                post.getRegisteredAt(),
                post.getUpdatedAt(),
                post.getDeletedAt()
        );
    }

    public void setLikeTotalCount(int likeTotalCount) {
        this.likeTotalCount = likeTotalCount;
    }

    public void setIsLike(boolean isLike) {
        this.isLike = isLike;
    }
}
