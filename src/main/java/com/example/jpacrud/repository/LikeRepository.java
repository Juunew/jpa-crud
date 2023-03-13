package com.example.jpacrud.repository;

import com.example.jpacrud.entity.LikeEntity;
import com.example.jpacrud.entity.PostEntity;
import com.example.jpacrud.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<LikeEntity, Long> {
    int countByPost(PostEntity post);

    Optional<Object> findByUserAndPost(UserEntity user, PostEntity post);

    List<LikeEntity> findUserInfoByPostId(Long postId);

    LikeEntity findByUserIdAndPostId(Long userId, Long postId);
}
