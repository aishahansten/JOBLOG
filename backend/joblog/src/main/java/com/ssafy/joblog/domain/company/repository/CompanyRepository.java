package com.ssafy.joblog.domain.company.repository;

import com.ssafy.joblog.domain.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
