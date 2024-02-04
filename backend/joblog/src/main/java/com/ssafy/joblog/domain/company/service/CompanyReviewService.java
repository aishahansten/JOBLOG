package com.ssafy.joblog.domain.company.service;

import com.ssafy.joblog.domain.company.dto.requestDto.CompanyReviewCreateRequestDto;
import com.ssafy.joblog.domain.company.dto.requestDto.CompanyReviewUpdateRequestDto;
import com.ssafy.joblog.domain.company.entity.Company;
import com.ssafy.joblog.domain.company.entity.CompanyReview;
import com.ssafy.joblog.domain.company.repository.CompanyRepository;
import com.ssafy.joblog.domain.company.repository.CompanyReviewRepository;
import com.ssafy.joblog.domain.user.entity.User;
import com.ssafy.joblog.domain.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CompanyReviewService {
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final CompanyReviewRepository companyReviewRepository;

    public void create(CompanyReviewCreateRequestDto companyReviewRequestDto){
        User user = userRepository.findById(companyReviewRequestDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("유저가 존재하지 않습니다"));
        Company company = companyRepository.findById(companyReviewRequestDto.getCompanyCode())
                .orElseThrow(() -> new EntityNotFoundException("회사가 존재하지 않습니다"));
        CompanyReview companyReview = companyReviewRequestDto.createReview(user, company);

        companyReviewRepository.save(companyReview);
    }


    public void update(CompanyReviewUpdateRequestDto companyReviewUpdateRequestDto) {
        User user = userRepository.findById(companyReviewUpdateRequestDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("유저가 존재하지 않습니다"));
        Company company = companyRepository.findById(companyReviewUpdateRequestDto.getCompanyCode())
                .orElseThrow(() -> new EntityNotFoundException("회사가 존재하지 않습니다"));
        CompanyReview companyReview  = companyReviewRepository.findById(companyReviewUpdateRequestDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("회사 리뷰가 존재하지 않습니다"));
        companyReview.updateReview(companyReviewUpdateRequestDto);
    }

    public void delete(Long reviewId) {
        CompanyReview companyReview  = companyReviewRepository.findById(reviewId)
                .orElseThrow(() -> new EntityNotFoundException("회사 리뷰가 존재하지 않습니다"));
        companyReview.deleteReview();
    }
}
