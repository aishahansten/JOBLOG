package com.ssafy.joblog.domain.company.service;

import com.ssafy.joblog.domain.company.dto.responseDto.CompanyResponseDto;
import com.ssafy.joblog.domain.company.dto.responseDto.CompanyReviewListResponseDto;
import com.ssafy.joblog.domain.company.entity.Company;
import com.ssafy.joblog.domain.company.entity.CompanyReview;
import com.ssafy.joblog.domain.company.repository.CompanyRepository;
import com.ssafy.joblog.domain.company.repository.CompanyReviewRepository;
import com.ssafy.joblog.domain.myRecruit.dto.response.ReviewPrivateResponseDto;
import com.ssafy.joblog.domain.myRecruit.dto.response.ReviewPublicResponseDto;
import com.ssafy.joblog.domain.myRecruit.entity.Selection;
import com.ssafy.joblog.domain.myRecruit.repository.SelectionRepository;
import com.ssafy.joblog.domain.recruit.dto.responseDto.RecruitListResponseDto;
import com.ssafy.joblog.domain.recruit.entity.Recruit;
import com.ssafy.joblog.domain.recruit.repository.RecruitRepository;
import com.ssafy.joblog.domain.user.entity.User;
import com.ssafy.joblog.domain.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final RecruitRepository recruitRepository;
    private final CompanyReviewRepository companyReviewRepository;
    private final SelectionRepository selectionRepository;
    private final UserRepository userRepository;

    public List<CompanyResponseDto> findAllCompany() {
        List<Company> allCompany = companyRepository.findAll();

        List<CompanyResponseDto> allCompanyDto = new ArrayList<>();
        for (Company company : allCompany) {
            allCompanyDto.add(CompanyResponseDto.fromEntity(company));
        }
        return allCompanyDto;
    }

    public CompanyResponseDto findCompany(Long companyCode) {
        Company company = companyRepository.findById(companyCode)
                .orElseThrow(()-> new IllegalArgumentException("해당 회사가 존재하지 않습니다"));
        return CompanyResponseDto.fromEntity(company);
    }


    public List<RecruitListResponseDto> findAllRecruitByCompany(Long companyCode) {
        Company company = companyRepository.findById(companyCode)
                .orElseThrow(()-> new IllegalArgumentException("해당 회사가 존재하지 않습니다"));
        List<Recruit> companyRecruit = recruitRepository.findAllRecruitByCompanyCompanyCode(company.getCompanyCode());

        List<RecruitListResponseDto> companyRecruitDto = new ArrayList<>();
        for (Recruit recruit : companyRecruit) {
            companyRecruitDto.add(RecruitListResponseDto.fromEntity(recruit));
        }
        return companyRecruitDto;
    }

    public List<CompanyReviewListResponseDto> findAllReviewByCompany(Long companyCode) {
        Company company = companyRepository.findById(companyCode)
                .orElseThrow(()-> new IllegalArgumentException("해당 회사가 존재하지 않습니다"));
        List<CompanyReview> companyReview = companyReviewRepository.findAllReviewByCompanyCompanyCodeAndIsDeleteIsFalse(company.getCompanyCode());

        List<CompanyReviewListResponseDto> companyReviewListResponseDto = new ArrayList<>();
        for (CompanyReview review : companyReview) {
            companyReviewListResponseDto.add(CompanyReviewListResponseDto.fromEntity(review));
        }
        return companyReviewListResponseDto;
    }

    public List<ReviewPublicResponseDto> findAllSelectionReviewByCompany(Long companyCode) {
        Company company = companyRepository.findById(companyCode)
                .orElseThrow(()-> new IllegalArgumentException("해당 회사가 존재하지 않습니다"));
        List<Selection> selectionList = selectionRepository.findAllSelectionByCompanyCodeAndIsDeleteIsFalseAndReviewOrNotIsTrue(companyCode);
        List<ReviewPublicResponseDto> reviewPublicResponseDto = new ArrayList<>();
        for (Selection selection : selectionList) {
            User user = userRepository.findById(selection.getUser().getId())
                    .orElseThrow(()-> new IllegalArgumentException("해당 사용자가 존재하지 않습니다"));
            reviewPublicResponseDto.add(ReviewPublicResponseDto.fromEntity(user, selection));
        }
        return reviewPublicResponseDto;
    }

}
