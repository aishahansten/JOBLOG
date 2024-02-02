package com.ssafy.joblog.domain.resume.repository;

import com.ssafy.joblog.domain.resume.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume, Integer> {
}