package com.ssafy.joblog.domain.user.repository;

import com.ssafy.joblog.domain.user.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Integer> {
    Optional<Follow> findByFollowerIdAndFollowingId(int id, int id1);

    List<Follow> findAllFollowByFollowerIdAndIsDeleteIsFalse(int userId);
}
