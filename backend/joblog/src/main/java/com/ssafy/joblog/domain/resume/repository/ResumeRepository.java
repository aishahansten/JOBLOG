package com.ssafy.joblog.domain.resume.repository;

import com.ssafy.joblog.domain.resume.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ResumeRepository extends JpaRepository<Resume, Integer> {
    List<Resume> findAllResumeByUserIdAndIsDeleteIsFalse(int userId);

    Optional<Resume> findByIdAndIsDeleteIsFalse(int resumeId);
}
