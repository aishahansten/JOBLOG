package com.ssafy.joblog.domain.essay.dto.request;

import com.ssafy.joblog.domain.essay.entity.EssayCategory;
import com.ssafy.joblog.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EssayCategoryCreateRequestDto {
    private int userId;
    private String categoryName;

    public EssayCategory createEssayCategory(User user, String categoryName) {
        return EssayCategory.builder()
                .user(user)
                .question_category_name(categoryName)
                .build();
    }
}
