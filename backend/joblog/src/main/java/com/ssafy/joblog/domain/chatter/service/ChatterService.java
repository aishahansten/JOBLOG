package com.ssafy.joblog.domain.chatter.service;

import com.ssafy.joblog.domain.chatter.dto.request.ChatterCreateRequestDto;
import com.ssafy.joblog.domain.chatter.dto.request.ChatterUpdateRequestDto;
import com.ssafy.joblog.domain.chatter.dto.response.ChatterResponseDto;
import com.ssafy.joblog.domain.chatter.entity.ChatterProfile;
import com.ssafy.joblog.domain.chatter.repository.ChatterRepository;
import com.ssafy.joblog.domain.user.entity.User;
import com.ssafy.joblog.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ChatterService {

    private final UserRepository userRepository;
    private final ChatterRepository chatterRepository;


    // 1. 채터 프로필 등록하기
    public void createChatter(ChatterCreateRequestDto chatterCreateRequestDto) {
        int userId = chatterCreateRequestDto.getUserId();
        if (chatterRepository.existsByUserId(userId)) {
            throw new IllegalArgumentException("이미 커피챗 프로필을 만들었습니다");
        }

        User user = userRepository.findById(chatterCreateRequestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다"));
        ChatterProfile chatter = chatterCreateRequestDto.createChatter(user);
        chatterRepository.save(chatter);
    }

    @Transactional(readOnly = true)
    // 2. 커피 채터 조회하기 (최신순, 삭제되지 않은 프로필만 조회)
    public List<ChatterResponseDto> getChatters() {
        List<ChatterProfile> chatters = chatterRepository.findAllByIsDeleteIsFalseOrderByModifiedDateDesc();
        List<ChatterResponseDto> getChattersList = new ArrayList<>();
        chatters.forEach(chatter -> getChattersList.add(ChatterResponseDto.builder()
                .userId(chatter.getId())
                .job(chatter.getJob())
                .career(chatter.getCareer())
                .description(chatter.getDescription())
                .build()));
        return getChattersList;
        // Entity to Dto
    }

    // 3. 커피 채터 상세 조회하기
    @Transactional(readOnly = true)
    public ChatterResponseDto getChatter(Integer userId) {
        Optional<ChatterProfile> optionalChatter = chatterRepository.findByUserIdAndIsDeleteIsFalse(userId);
        ChatterProfile chatter = optionalChatter.orElseThrow(()->new NotFoundException("커피챗을 찾을 수 없습니다"));
        ChatterResponseDto chatterResponseDto = ChatterResponseDto.builder()
                .userId(chatter.getUser().getId())
                .job(chatter.getJob())
                .career(chatter.getCareer())
                .description(chatter.getDescription())
                .build();
        return chatterResponseDto;
    }

    // 4. 커피 채터 수정
    @Transactional
    public void updateChatter(ChatterUpdateRequestDto chatterUpdateRequestDto) {
        Optional<ChatterProfile> optionalChatter = chatterRepository.findByUserIdAndIsDeleteIsFalse(chatterUpdateRequestDto.getUserId());
        ChatterProfile chatter = optionalChatter.orElseThrow(()->new IllegalArgumentException("채터가 존재하지 않습니다"));
        chatter.updateChatter(chatterUpdateRequestDto);
    }

    // 5. 커피 채터 삭제
    @Transactional
    public void markDeletedChatter(int userId) {
        Optional<ChatterProfile> optionalChatter = chatterRepository.findByUserIdAndIsDeleteIsFalse(userId);
        ChatterProfile chatter = optionalChatter
                .orElseThrow(() -> new IllegalArgumentException("해당 채터를 찾을 수 없습니다"));
        chatterRepository.markDeletedChatter(chatter.getId());
    }


}
