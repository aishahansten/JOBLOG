package com.ssafy.joblog.domain.myRecruit.service;

import com.ssafy.joblog.domain.company.dto.responseDto.CompanyReviewListResponseDto;
import com.ssafy.joblog.domain.company.entity.CompanyReview;
import com.ssafy.joblog.domain.myRecruit.dto.request.MyRecruitRequestDto;
import com.ssafy.joblog.domain.myRecruit.dto.request.RecruitScrapRequestDto;
import com.ssafy.joblog.domain.myRecruit.dto.response.MyRecruitResponseDto;
import com.ssafy.joblog.domain.myRecruit.dto.response.ReviewPrivateResponseDto;
import com.ssafy.joblog.domain.myRecruit.dto.response.SelectionListResponseDto;
import com.ssafy.joblog.domain.myRecruit.entity.MyRecruit;
import com.ssafy.joblog.domain.myRecruit.entity.Selection;
import com.ssafy.joblog.domain.myRecruit.repository.MyRecruitRepository;
import com.ssafy.joblog.domain.myRecruit.repository.SelectionRepository;
import com.ssafy.joblog.domain.recruit.entity.Recruit;
import com.ssafy.joblog.domain.recruit.repository.RecruitRepository;
import com.ssafy.joblog.domain.user.entity.User;
import com.ssafy.joblog.domain.user.repository.UserRepository;
import jdk.swing.interop.SwingInterOpUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MyRecruitService {
    private final MyRecruitRepository myRecruitRepository;
    private final UserRepository userRepository;
    private final RecruitRepository recruitRepository;
    private final SelectionRepository selectionRepository;

    public void createScrap(RecruitScrapRequestDto recruitScrapRequestDto) {
        User user = userRepository.findById(recruitScrapRequestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다"));
        Recruit recruit = recruitRepository.findById(recruitScrapRequestDto.getRecruitId())
                .orElseThrow(() -> new IllegalArgumentException("해당 채용공고가 존재하지 않습니다"));

        Optional<MyRecruit> myRecruitInfo = myRecruitRepository.findByUserIdAndRecruitId(user.getId(), recruit.getId());
        if (myRecruitInfo.isPresent()) { //기존 스크랩 정보가 있는 경우
            if (myRecruitInfo.get().isDelete()) {
                myRecruitInfo.get().updateScrap();
            } else {
                myRecruitInfo.get().deleteScrap();
            }
        } else { //새로운 스크랩정보 생성
            MyRecruit myRecruit = recruitScrapRequestDto.createScrap(user, recruit);
            myRecruitRepository.save(myRecruit);
        }
    }

    public List<MyRecruitResponseDto> findAllMyRecruit(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다"));
        List<MyRecruit> myRecruits = myRecruitRepository.findAllMyRecruitByUserIdAndIsDeleteIsFalseOrderByCreatedDateDesc(user.getId());

        List<MyRecruitResponseDto> myRecruitResponseDto = new ArrayList<>();
        for (MyRecruit myRecruit : myRecruits) {
            List<Selection> selections = selectionRepository.findAllSelectionByMyRecruitIdAndIsDeleteIsFalseOrderByStep(myRecruit.getId());
            List<SelectionListResponseDto> selectionListResponseDto = new ArrayList<>();
            for (Selection selection : selections) {
                selectionListResponseDto.add(SelectionListResponseDto.fromEntity(selection, ReviewPrivateResponseDto.fromEntity(selection)));
            }
            myRecruitResponseDto.add(MyRecruitResponseDto.fromEntityList(myRecruit, selectionListResponseDto));
        }
        return myRecruitResponseDto;
    }

    public MyRecruitResponseDto findMyRecruit(int myRecruitId) {
        MyRecruit myRecruit = myRecruitRepository.findByIdAndIsDeleteIsFalse(myRecruitId)
                .orElseThrow(() -> new IllegalArgumentException("해당 마이채용 정보가 존재하지 않습니다"));
        return MyRecruitResponseDto.fromEntity(myRecruit);
    }

    public void create(MyRecruitRequestDto myRecruitRequestDto) {
        User user = userRepository.findById(myRecruitRequestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다"));
        MyRecruit myRecruit = myRecruitRequestDto.createMyRecruit(user);
        myRecruitRepository.save(myRecruit);
    }

    public void update(MyRecruitRequestDto myRecruitRequestDto) {
        User user = userRepository.findById(myRecruitRequestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다"));
        MyRecruit myRecruit = myRecruitRepository.findByIdAndIsDeleteIsFalse(myRecruitRequestDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 마이채용 정보가 존재하지 않습니다"));
        if (myRecruit.getCompanyCode() != null) {
            myRecruit.updateMyRecruitScrap(myRecruitRequestDto);
        } else {
            myRecruit.updateMyRecruitNonScrap(myRecruitRequestDto);
        }
    }

    public void delete(int myRecruitId) {
        MyRecruit myRecruit = myRecruitRepository.findByIdAndIsDeleteIsFalse(myRecruitId)
                .orElseThrow(() -> new IllegalArgumentException("해당 마이채용 정보가 존재하지 않습니다"));
        myRecruit.deleteMyRecruit();
    }

}
