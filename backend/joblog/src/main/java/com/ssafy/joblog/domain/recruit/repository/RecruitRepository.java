package com.ssafy.joblog.domain.recruit.repository;

import com.ssafy.joblog.domain.recruit.entity.Recruit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;


@Repository
public interface RecruitRepository extends JpaRepository<Recruit, Long>, JpaSpecificationExecutor<Recruit> {
    List<Recruit> findAllRecruitByCompanyCodeOrderByCreatedDateDesc(Long companyCode);

}
