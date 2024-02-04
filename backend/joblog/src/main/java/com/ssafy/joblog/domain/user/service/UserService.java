package com.ssafy.joblog.domain.user.service;

import com.ssafy.joblog.domain.user.dto.request.UserUpdateRequestDto;
import com.ssafy.joblog.domain.user.dto.response.UserResponseDto;
import com.ssafy.joblog.domain.user.entity.User;
import com.ssafy.joblog.domain.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponseDto findUser(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("유저가 존재하지 않습니다"));
        return UserResponseDto.fromEntity(user);
    }

    public void update(UserUpdateRequestDto userUpdateRequestDto) {
        User user = userRepository.findById(userUpdateRequestDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("유저가 존재하지 않습니다"));
        user.updateUser(userUpdateRequestDto);
    }

    public void delete(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("유저가 존재하지 않습니다"));
        user.deleteUser();
    }
}
