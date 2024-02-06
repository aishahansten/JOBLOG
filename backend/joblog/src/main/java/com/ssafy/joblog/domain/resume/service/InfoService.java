package com.ssafy.joblog.domain.resume.service;

import com.ssafy.joblog.domain.resume.dto.request.InfoCreateRequestDto;
import com.ssafy.joblog.domain.resume.dto.request.InfoUpdateRequestDto;
import com.ssafy.joblog.domain.resume.dto.response.InfoResponseDto;
import com.ssafy.joblog.domain.resume.entity.Info;
import com.ssafy.joblog.domain.resume.repository.InfoRepository;
import com.ssafy.joblog.domain.user.entity.User;
import com.ssafy.joblog.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class InfoService {
    private final UserRepository userRepository;
    private final InfoRepository infoRespository;

    public void create(InfoCreateRequestDto infoCreateRequestDto) {
        User user = userRepository.findById(infoCreateRequestDto.getUserId())
                .orElseThrow(()-> new IllegalArgumentException("해당 사용자가 존재하지 않습니다"));
        Info info = infoCreateRequestDto.createInfo(user);
        infoRespository.save(info);
    }

    public void update(InfoUpdateRequestDto infoUpdateRequestDto) {
        User user = userRepository.findById(infoUpdateRequestDto.getUserId())
                .orElseThrow(()-> new IllegalArgumentException("해당 사용자가 존재하지 않습니다"));
        Info info = infoRespository.findByIdAndIsDeleteIsFalse(infoUpdateRequestDto.getInfoId())
                .orElseThrow(()-> new IllegalArgumentException("해당 정보가 존재하지 않습니다"));
        info.updateInfo(infoUpdateRequestDto);
    }

    public void delete(int infoId) {
        Info info = infoRespository.findByIdAndIsDeleteIsFalse(infoId)
                .orElseThrow(()-> new IllegalArgumentException("해당 정보가 존재하지 않습니다"));
        info.deleteInfo();
    }

    public List<InfoResponseDto> findAllInfo(int userId) {
        List<Info> allInfo = infoRespository.findAllInfoByUserIdAndResumeIdEqualsAndIsDeleteIsFalseOrderByInfoCategory(userId, 0);

        List<InfoResponseDto> allInfoDto = new ArrayList<>();
        for (Info info : allInfo) {
            allInfoDto.add(InfoResponseDto.fromEntity(info));
        }
        return allInfoDto;
    }
}
