package com.ssafy.joblog.domain.myRecruit.repository;

import com.ssafy.joblog.domain.myRecruit.entity.Selection;
import com.ssafy.joblog.domain.resume.entity.Info;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SelectionRepository  extends JpaRepository<Selection, Integer> {
}
