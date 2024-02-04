package com.ssafy.joblog.domain.recruit.repository;

import com.ssafy.joblog.domain.recruit.entity.JobCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface JobCategoryRepository extends JpaRepository<JobCategory, Integer> {
    List<JobCategory> findAllByParentIsNull();
}
