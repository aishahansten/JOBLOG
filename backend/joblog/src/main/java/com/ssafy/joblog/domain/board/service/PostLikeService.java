package com.ssafy.joblog.domain.board.service;

import com.ssafy.joblog.domain.board.dto.request.PostLikeRequestDto;
import com.ssafy.joblog.domain.board.entity.Post;
import com.ssafy.joblog.domain.board.entity.PostLike;
import com.ssafy.joblog.domain.board.repository.PostLikeRepository;
import com.ssafy.joblog.domain.board.repository.PostRepository;
import com.ssafy.joblog.domain.user.entity.User;
import com.ssafy.joblog.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public void likePost(PostLikeRequestDto postLikeRequestDto) {
        Post post = postRepository.findById(postLikeRequestDto.getPostId()).orElseThrow(() -> new IllegalArgumentException("해당하는 게시글이 없습니다."));
        User user = userRepository.findById(postLikeRequestDto.getUserId()).orElseThrow(() -> new IllegalArgumentException("해당하는 유저가 없습니다."));
        PostLike postLike = PostLike.builder()
                .user(user)
                .post(post)
                .build();

        // 이미 좋아요를 누른 경우(post_like db에 저장되어있는 경우)
        if (postLikeRepository.existsByUserAndPost(user, post)) {
            postLikeRepository.deleteByUserAndPost(user, post);
        } else {
            postLikeRepository.save(postLike);
        }
    }
}
