package com.ssafy.joblog.domain.chatter.repository;

import com.ssafy.joblog.domain.chatter.entity.ChatterProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatterRepository extends JpaRepository<ChatterProfile, Integer> {
    // 전체조회
    List<ChatterProfile> findAllByIsDeleteIsFalseOrderByCreatedDateDesc();
    //상세조회
    Optional<ChatterProfile> findByUserIdAndIsDeleteIsFalse(Integer userId);
}
