package com.ssafy.joblog.domain.myRecruit.service;

import com.ssafy.joblog.domain.myRecruit.dto.request.SelectionRequestDto;
import com.ssafy.joblog.domain.myRecruit.entity.MyRecruit;
import com.ssafy.joblog.domain.myRecruit.entity.Selection;
import com.ssafy.joblog.domain.myRecruit.repository.MyRecruitRepository;
import com.ssafy.joblog.domain.myRecruit.repository.SelectionRepository;
import com.ssafy.joblog.domain.recruit.repository.RecruitRepository;
import com.ssafy.joblog.domain.user.entity.User;
import com.ssafy.joblog.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SelectionService {
    private final MyRecruitRepository myRecruitRepository;
    private final UserRepository userRepository;
    private final SelectionRepository selectionRepository;

    public void create(SelectionRequestDto selectionRequestDto) {
        User user = userRepository.findById(selectionRequestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다"));
        MyRecruit myRecruit = myRecruitRepository.findByIdAndIsDeleteIsFalse(selectionRequestDto.getMyRecruitId())
                .orElseThrow(() -> new IllegalArgumentException("해당 마이 채용이 존재하지 않습니다"));
        Selection selection = selectionRequestDto.createSelection(user, myRecruit);
        selectionRepository.save(selection);
    }

    public void update(SelectionRequestDto selectionRequestDto) {
        User user = userRepository.findById(selectionRequestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다"));
        MyRecruit myRecruit = myRecruitRepository.findByIdAndIsDeleteIsFalse(selectionRequestDto.getMyRecruitId())
                .orElseThrow(() -> new IllegalArgumentException("해당 마이채용 정보가 존재하지 않습니다"));
        Selection selection = selectionRepository.findByIdAndIsDeleteIsFalse(selectionRequestDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 전형 정보가 존재하지 않습니다"));
        selection.updateSelection(selectionRequestDto);
    }

    public void delete(int selectionId) {
        Selection selection = selectionRepository.findByIdAndIsDeleteIsFalse(selectionId)
                .orElseThrow(() -> new IllegalArgumentException("해당 전형 정보가 존재하지 않습니다"));
        selection.deleteSelection();
    }

}
