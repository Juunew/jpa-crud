package com.example.jpacrud.controller;

import com.example.jpacrud.config.annotation.InfoUser;
import com.example.jpacrud.dto.auth.UserInfo;
import com.example.jpacrud.dto.like.LikeDto;
import com.example.jpacrud.dto.post.PostDto;
import com.example.jpacrud.dto.post.PostRequestDto;
import com.example.jpacrud.dto.response.ResponseBody;
import com.example.jpacrud.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
@RestController
public class PostController {

    private final PostService postService;

    // 게시글 작성
    @PostMapping("")
    public ResponseEntity<ResponseBody> createPost(@RequestBody PostRequestDto dto,
                                                   @InfoUser UserInfo userInfo) {
        postService.createPost(dto, userInfo);
        return ResponseEntity.ok(ResponseBody.success("success"));
    }

    // 게시글 목록 조회
    @GetMapping("")
    public ResponseEntity<ResponseBody> getPostList(Pageable pageable,
                                                    @InfoUser UserInfo userInfo) {
        Page<PostDto> result = postService.getPostList(pageable, userInfo);
        return ResponseEntity.ok(ResponseBody.success(result));
    }

    // 게시글 수정
    @PutMapping("/{postId}")
    public ResponseEntity<ResponseBody> putPost(@PathVariable Long postId,
                                                @RequestBody PostRequestDto dto,
                                                @InfoUser UserInfo userInfo) {
        postService.modifyPost(postId, dto, userInfo);
        return ResponseEntity.ok(ResponseBody.success("success"));
    }

    // 게시글 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<ResponseBody> removePost(@PathVariable Long postId,
                                                   @InfoUser UserInfo userInfo) {
        postService.deletePost(postId, userInfo);
        return ResponseEntity.ok(ResponseBody.success("success"));
    }

    // 게시글 좋아요
    @PostMapping("/{postId}/likes")
    public ResponseEntity<ResponseBody> like(@PathVariable Long postId,
                                             @InfoUser UserInfo userInfo) {
        postService.likePost(postId, userInfo);
        return ResponseEntity.ok(ResponseBody.success("success"));
    }

    // 좋아요 회원 목록
    @GetMapping("/{postId}/likes")
    public ResponseEntity<ResponseBody> likeUserList(@PathVariable Long postId) {
        List<LikeDto> result = postService.getLikeUserListByPost(postId);
        return ResponseEntity.ok(ResponseBody.success(result));
    }
}
