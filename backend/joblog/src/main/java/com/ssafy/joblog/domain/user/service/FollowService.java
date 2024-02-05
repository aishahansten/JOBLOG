package com.ssafy.joblog.domain.user.service;

import com.ssafy.joblog.domain.user.dto.request.FollowRequestDto;
import com.ssafy.joblog.domain.user.dto.response.FollowListResponseDto;
import com.ssafy.joblog.domain.user.entity.Follow;
import com.ssafy.joblog.domain.user.entity.User;
import com.ssafy.joblog.domain.user.repository.FollowRepository;
import com.ssafy.joblog.domain.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class FollowService {
    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    public void followChatter(FollowRequestDto followRequestDto) {
        User follower = userRepository.findById(followRequestDto.getFollowerId())
                .orElseThrow(() -> new EntityNotFoundException("팔로워 유저가 존재하지 않습니다"));
        User following = userRepository.findById(followRequestDto.getFollowingId())
                .orElseThrow(() -> new EntityNotFoundException("팔로잉 유저가 존재하지 않습니다"));

        if(follower == following) { //자신을 팔로우하는 경우
            throw new IllegalStateException("자신은 팔로우할 수 없습니다");
        }

        Optional<Follow> followInfo = followRepository.findByFollowerIdAndFollowingId(follower.getId(), following.getId());
        if(followInfo.isPresent()) { //기존 팔로우 정보가 있는 경우
            if( followInfo.get().isDelete()){
                followInfo.get().updateFollow();
            }
            else{
                followInfo.get().deleteFollow();
            }
        }
        else { //새로운 팔로우정보 생성
            Follow follow = followRequestDto.createFollow(follower, following);
            followRepository.save(follow);
        }
    }

    public List<FollowListResponseDto> findAllFollowingListByUserId(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("팔로워 유저가 존재하지 않습니다"));
        List<Follow> followingList = followRepository.findAllFollowByFollowerIdAndIsDeleteIsFalse(userId);

        List<FollowListResponseDto> followListResponseDto = new ArrayList<>();
        for(Follow following: followingList){
            followListResponseDto.add(FollowListResponseDto.fromEntity(following));
        }
        return followListResponseDto;
    }
}
