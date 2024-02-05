package com.ssafy.joblog.domain.resume.repository;

import com.ssafy.joblog.domain.resume.entity.Info;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InfoRepository extends JpaRepository<Info, Integer> {

    List<Info> findAllInfoByUserIdAndResumeIdEqualsAndIsDeleteIsFalseOrderByInfoCategory(int userId, int resumeId);

    List<Info> findAllInfoByResumeIdAndIsDeleteIsFalse(int resumeId);

    Optional<Info> findByIdAndIsDeleteIsFalse(int infoId);
}
