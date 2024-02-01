package com.ssafy.joblog.domain.board.service;

import com.ssafy.joblog.domain.board.dto.request.PostCreateRequestDto;
import com.ssafy.joblog.domain.board.dto.request.PostUpdateRequestDto;
import com.ssafy.joblog.domain.board.dto.response.PostResponseDto;
import com.ssafy.joblog.domain.board.entity.Post;
import com.ssafy.joblog.domain.board.entity.PostCategory;
import com.ssafy.joblog.domain.board.entity.PostLike;
import com.ssafy.joblog.domain.board.repository.PostLikeRepository;
import com.ssafy.joblog.domain.board.repository.PostRepository;
import com.ssafy.joblog.domain.user.entity.User;
import com.ssafy.joblog.domain.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostLikeRepository postLikeRepository;

    // 1. 게시글 작성
    public void createPost(PostCreateRequestDto postCreateRequestDto) {
        // 작성한 유저 불러옴
        User user = userRepository.findById(postCreateRequestDto.getUserId());
        Post post = postCreateRequestDto.createPost(user);
        postRepository.save(post);
    }

    //2. 카테고리별 게시글 목록 조회
    @Transactional(readOnly = true)
    public List<PostResponseDto> getPosts(PostCategory category) {
        List<Post> posts = postRepository.findByCategory(category);
        List<PostResponseDto> getPostsList = new ArrayList<>();
        posts.forEach(post -> getPostsList.add(PostResponseDto.builder()
                .postId(post.getId())
                .userId(post.getUser().getId())
                .category(post.getCategory())
                .title(post.getTitle())
                .content(post.getContent())
                .hit(post.getHit())
                .createdDate(post.getCreatedDate())
                .modifiedDate(post.getModifiedDate())
                .totalComment(post.getCommentCount())
                .totalLike(post.getLikeCount())
                .build()));
        return getPostsList;
    }

    //3. 게시글 상세 조회
    @Transactional
    public PostResponseDto getPost(int id) {
        Post post = postRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("해당 게시글을 찾을 수 없습니다");
        });
        post.addHit(1);
        PostResponseDto postResponseDto = PostResponseDto.builder()
                .postId(post.getId())
                .userId(post.getUser().getId())
                .category(post.getCategory())
                .title(post.getTitle())
                .content(post.getContent())
                .hit(post.getHit())
                .createdDate(post.getCreatedDate())
                .modifiedDate(post.getModifiedDate())
                .totalComment(post.getCommentCount())
                .totalLike(post.getLikeCount())
                .build();
        return postResponseDto;
    }

    //4. 게시글 수정
    @Transactional
    public void updatePost(PostUpdateRequestDto postUpdateRequestDto) {
        Post post = postRepository.findById(postUpdateRequestDto.getPostId())
                .orElseThrow(() -> new EntityNotFoundException("게시글이 존재하지 않습니다"));
        post.updatePost(postUpdateRequestDto);
    }

    //5. 게시글 삭제(isDelete 1<->0)
    @Transactional
    public void markDeletedPost(int postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> {
            return new IllegalArgumentException("해당 게시글을 찾을 수 없습니다");
        });
        postRepository.delete(post);
    }
}