package com.ssafy.joblog.domain.company.repository;

import com.ssafy.joblog.domain.company.entity.CompanyReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyReviewRepository extends JpaRepository<CompanyReview, Long> {

    List<CompanyReview> findAllReviewByCompanyCompanyCodeAndIsDeleteIsFalseOrderByCreatedDateDesc(Long companyCode);
}
