package com.example.jpacrud.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "likes")
@Entity
public class LikeEntity extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "likes_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_id")
    private PostEntity post;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private LikeEntity(PostEntity post) {
        this.post = post;
    }

    private LikeEntity(PostEntity post, UserEntity user) {
        this.post = post;
        this.user = user;
    }

    public static LikeEntity of(PostEntity post, UserEntity user) {
        return new LikeEntity(post, user);
    }

    public static LikeEntity likeCount(PostEntity post) {
        return new LikeEntity(post);
    }
}
