package com.example.jpacrud.service;

import com.example.jpacrud.constant.AccountType;
import com.example.jpacrud.constant.errorType.PostError;
import com.example.jpacrud.constant.errorType.UserError;
import com.example.jpacrud.dto.auth.UserInfo;
import com.example.jpacrud.dto.like.LikeDto;
import com.example.jpacrud.dto.post.PostDto;
import com.example.jpacrud.dto.post.PostRequestDto;
import com.example.jpacrud.entity.LikeEntity;
import com.example.jpacrud.entity.PostEntity;
import com.example.jpacrud.entity.UserEntity;
import com.example.jpacrud.exception.PostException;
import com.example.jpacrud.exception.UserException;
import com.example.jpacrud.repository.LikeRepository;
import com.example.jpacrud.repository.PostRepository;
import com.example.jpacrud.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;

    @Transactional
    public void createPost(PostRequestDto dto, UserInfo userInfo) {
        UserEntity user = getUserInfoOrException(userInfo);
        postRepository.save(PostEntity.of(dto.getTitle(), dto.getContent(), user));
    }

    @Transactional
    public void modifyPost(Long postId, PostRequestDto dto, UserInfo userInfo) {
        PostEntity post = getPostInfoOrException(postId);
        UserEntity user = getUserInfoOrException(userInfo);

        if (!post.getUser().equals(user)) {
            throw new UserException(UserError.UNAUTHORIZED_USER);
        }

        if (dto.getTitle() != null) {
            post.setTitle(dto.getTitle());
        }

        if (dto.getContent() != null) {
            post.setContent(dto.getContent());
        }
    }

    @Transactional
    public void deletePost(Long postId, UserInfo userInfo) {
        PostEntity post = getPostInfoOrException(postId);
        UserEntity user = getUserInfoOrException(userInfo);

        if (post.getUser() != user) {
            throw new UserException(UserError.UNAUTHORIZED_USER);
        }

        postRepository.delete(post);
    }

    public Page<PostDto> getPostList(Pageable pageable, UserInfo userInfo) {
        Page<PostDto> postDto = postRepository.findAll(pageable).map(PostDto::fromEntity);
        if (checkUserType(userInfo)) {
            for (PostDto post : postDto) {
                post.setLikeTotalCount(likeCount(post.getPostId()));
            }
        } else {
            Long userId = getUserId(userInfo);

            for (PostDto post : postDto) {
                post.setLikeTotalCount(likeCount(post.getPostId()));
                post.setIsLike(likeCheck(userId, post.getPostId()));
            }
        }

        return postDto;
    }

    @Transactional
    public void likePost(Long postId, UserInfo userInfo) {
        PostEntity post = getPostInfoOrException(postId);
        UserEntity user = getUserInfoOrException(userInfo);

        likeRepository.findByUserAndPost(user, post).ifPresent(it -> {
            throw new UserException(UserError.ALREADY_LIKED);
        });

        likeRepository.save(LikeEntity.of(post, user));
    }

    public List<LikeDto> getLikeUserListByPost(Long postId) {
        return likeRepository.findUserInfoByPostId(postId)
                .stream()
                .map(LikeDto::fromEntity)
                .collect(Collectors.toList());
    }

    // 게시글 좋아요 갯수 count
    private int likeCount(Long postId) {
        PostEntity post = getPostInfoOrException(postId);
        return likeRepository.countByPost(post);
    }

    // 로그인 회원이 좋아요한 게시물인지 check
    private boolean likeCheck(Long userId, Long postId) {
        LikeEntity like = likeRepository.findByUserIdAndPostId(userId, postId);
        return like != null;
    }

    // UserType check
    private boolean checkUserType(UserInfo userInfo) {
        return userInfo.getAccountType() == AccountType.GENERAL;
    }

    // 게시글 검증
    private PostEntity getPostInfoOrException(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new PostException(PostError.POST_NOT_FOUND));
    }

    // 회원 검증
    private UserEntity getUserInfoOrException(UserInfo userInfo) {
        Long userId = getUserId(userInfo);
        return userRepository.findById(userId).orElseThrow(() -> new UserException(UserError.USER_NOT_FOUND));
    }

    // 로그인 회원 id(pk) 조회
    private Long getUserId(UserInfo userInfo) {
        UserEntity user = userRepository.findByAccountId(userInfo.getAccountId());
        return user.getId();
    }
}

