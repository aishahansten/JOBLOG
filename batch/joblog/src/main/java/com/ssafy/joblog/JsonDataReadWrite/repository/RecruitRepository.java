package com.ssafy.joblog.JsonDataReadWrite.repository;

import com.ssafy.joblog.JsonDataReadWrite.entity.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecruitRepository extends JpaRepository<Recruit, Long> {

    Optional<Recruit> findByJobId(Long jobId);
}
