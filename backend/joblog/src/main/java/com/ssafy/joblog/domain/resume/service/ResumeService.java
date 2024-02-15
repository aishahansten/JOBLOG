package com.ssafy.joblog.domain.resume.service;

import com.ssafy.joblog.domain.resume.dto.request.ResumeCreateRequestDto;
import com.ssafy.joblog.domain.resume.dto.request.ResumeUpdateRequestDto;
import com.ssafy.joblog.domain.resume.dto.response.InfoResponseDto;
import com.ssafy.joblog.domain.resume.dto.response.ResumeListResponseDto;
import com.ssafy.joblog.domain.resume.dto.response.ResumeResponseDto;
import com.ssafy.joblog.domain.resume.dto.response.ResumeWithInfoResponseDto;
import com.ssafy.joblog.domain.resume.entity.Info;
import com.ssafy.joblog.domain.resume.entity.Resume;
import com.ssafy.joblog.domain.resume.repository.InfoRepository;
import com.ssafy.joblog.domain.resume.repository.ResumeRepository;
import com.ssafy.joblog.domain.user.entity.User;
import com.ssafy.joblog.domain.user.repository.UserRepository;
import com.ssafy.joblog.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ResumeService {
    private final UserRepository userRepository;
    private final ResumeRepository resumeRepository;
    private final InfoRepository infoRepository;
    private final UserService userService;

    public List<ResumeListResponseDto> findAllResume(int userId) {
        List<Resume> allResume = resumeRepository.findAllResumeByUserIdAndIsDeleteIsFalse(userId);

        List<ResumeListResponseDto> allResumeDto = new ArrayList<>();
        for (Resume resume : allResume) {
            allResumeDto.add(ResumeListResponseDto.fromEntity(resume));
        }
        return allResumeDto;
    }

    public ResumeWithInfoResponseDto findResumeWithInfo(int resumeId) {
        Resume resume = resumeRepository.findByIdAndIsDeleteIsFalse(resumeId)
                .orElseThrow(()-> new IllegalArgumentException("해당 이력서가 존재하지 않습니다"));
        ResumeResponseDto resumeResponseDto = ResumeResponseDto.fromEntity(resume);

        List<Info> infoList = infoRepository.findAllInfoByResumeIdAndIsDeleteIsFalse(resume.getId());
        List<InfoResponseDto> allInfoDto = new ArrayList<>();
        for (Info info : infoList) {
            allInfoDto.add(InfoResponseDto.fromEntity(info));
        }
        return new ResumeWithInfoResponseDto(resume.getUser(), resumeResponseDto, userService.getFile(resume.getUser().getId()), allInfoDto);
    }

    public void create(ResumeCreateRequestDto resumeCreateRequestDto) {
        User user = userRepository.findById(resumeCreateRequestDto.getUserId())
                .orElseThrow(()-> new IllegalArgumentException("해당 사용자가 존재하지 않습니다"));
        Resume resume = resumeCreateRequestDto.createResume(user);
        resume = resumeRepository.save(resume); // 이력서 생성
        int resumeId  = resume.getId();

        List<Integer> infoList = resumeCreateRequestDto.getInfoList();
        for (int infoIdx: infoList){
            Info info = infoRepository.findById(infoIdx)
                    .orElseThrow(()-> new IllegalArgumentException("해당 정보가 존재하지 않습니다"));
            if(user.getId() != info.getUser().getId()) throw new IllegalArgumentException("해당 정보의 권한이 없습니다");
            Info resumeInfo = info.createResumeInfo(user, resumeId);
            infoRepository.save(resumeInfo);
        }
    }

    public void update(ResumeUpdateRequestDto resumeUpdateRequestDto) {
        User user = userRepository.findById(resumeUpdateRequestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다"));
        Resume resume = resumeRepository.findByIdAndIsDeleteIsFalse(resumeUpdateRequestDto.getResumeId())
                .orElseThrow(()-> new IllegalArgumentException("해당 이력서가 존재하지 않습니다"));
        resume.updateResume(resumeUpdateRequestDto);

        int resumeId  = resume.getId();
        List<Info> oldInfoList = infoRepository.findAllInfoByResumeIdAndIsDeleteIsFalse(resume.getId());
        for (Info info: oldInfoList) info.deleteInfo();  //기존 정보 삭제

        List<Integer> infoList = resumeUpdateRequestDto.getInfoList();
        for (int infoIdx: infoList){
            Info info = infoRepository.findById(infoIdx)
                    .orElseThrow(()-> new IllegalArgumentException("해당 정보가 존재하지 않습니다"));
            if(user.getId() != info.getUser().getId()) throw new IllegalArgumentException("해당 정보의 권한이 없습니다");
            Info resumeInfo = info.createResumeInfo(user, resumeId);
            infoRepository.save(resumeInfo);
        }
    }

    public void delete(int resumeId) {
        Resume resume = resumeRepository.findByIdAndIsDeleteIsFalse(resumeId)
                .orElseThrow(()-> new IllegalArgumentException("해당 이력서가 존재하지 않습니다"));
        resume.deleteResume();
    }
}
