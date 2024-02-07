package com.ssafy.joblog.domain.recruit.Specification;

import com.ssafy.joblog.domain.recruit.entity.Recruit;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class RecruitSpecification {
    public static Specification<Recruit> searchRecruit(Map<String, Object> searchKeys) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            List<Predicate> jobpredicates = new ArrayList<>();
            for (String key : searchKeys.keySet()) {
                String keyData = (String) searchKeys.get(key);
                if (key.equals("keyword")) { //공고명 또는 기업명
                    predicates.add(criteriaBuilder.or(
                            criteriaBuilder.like(root.get("title"), "%" + keyData + "%"),
                            criteriaBuilder.like(root.get("companyName"), "%" + keyData + "%")));
                } else if (key.equals("jobCategory")) {  //직무 카테고리, 다중선택가능
                    List<String> jobIds = Arrays.asList(keyData.split(","));
                    for (String jobId : jobIds) {
                        jobpredicates.add(criteriaBuilder.equal(root.get("jobCategoryRecruits").get("jobCategory").get("jobCode"), Integer.parseInt(jobId)));
                    }

                } else {
                    System.out.println(root.get(key));
                    predicates.add(criteriaBuilder.equal(root.get(key), keyData));
                }
            }
            if (jobpredicates.isEmpty()) {
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
            return criteriaBuilder.and(
                    criteriaBuilder.and(predicates.toArray(new Predicate[0])),
                    criteriaBuilder.or(jobpredicates.toArray(new Predicate[0])));
        });
    }
}
