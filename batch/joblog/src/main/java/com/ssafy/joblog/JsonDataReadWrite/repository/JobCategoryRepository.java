package com.ssafy.joblog.JsonDataReadWrite.repository;

import com.ssafy.joblog.JsonDataReadWrite.entity.JobCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobCategoryRepository extends JpaRepository<JobCategory, Integer> {
    List<JobCategory> findAllByParentIsNull();
}
