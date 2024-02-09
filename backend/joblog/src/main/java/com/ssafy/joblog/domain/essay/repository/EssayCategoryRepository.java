package com.ssafy.joblog.domain.essay.repository;

import com.ssafy.joblog.domain.essay.entity.Essay;
import com.ssafy.joblog.domain.essay.entity.EssayCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EssayCategoryRepository extends JpaRepository<EssayCategory, Integer> {

    List<EssayCategory> findByUserIdAndIsDeleteIsFalse(Integer userId);

    @Modifying
    @Query("UPDATE EssayCategory category SET category.isDelete = true WHERE category.id = :id")
    void markDeletedEssayCategory(@Param("id") Integer id);


    EssayCategory findEssayCategoryByQuestionCategoryNameAndIsDeleteIsFalse(String question_category_name);
}
