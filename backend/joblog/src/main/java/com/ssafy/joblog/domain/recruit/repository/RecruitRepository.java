package com.ssafy.joblog.domain.recruit.repository;

import com.ssafy.joblog.domain.recruit.entity.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RecruitRepository extends JpaRepository<Recruit, Long>, JpaSpecificationExecutor<Recruit> {
    List<Recruit> findAllRecruitByCompanyCode(Long companyCode);

}
