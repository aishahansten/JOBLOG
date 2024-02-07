package com.ssafy.joblog.JsonDataReadWrite.repository;

import com.ssafy.joblog.JsonDataReadWrite.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
