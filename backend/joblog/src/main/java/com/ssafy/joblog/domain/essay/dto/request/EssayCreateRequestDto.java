package com.ssafy.joblog.domain.essay.dto.request;

import com.ssafy.joblog.domain.essay.entity.Essay;
import com.ssafy.joblog.domain.essay.entity.EssayCategory;
import com.ssafy.joblog.domain.recruit.entity.Recruit;
import com.ssafy.joblog.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EssayCreateRequestDto {

    private int userId;
    private Long recruitId;
    private Integer categoryId;  // Integer로 선택적 입력받기, null 허용
    private String question;
    private String answer;

    // DTO to Entity
    public Essay createEssay(User user, Recruit recruit, EssayCategory essayCategory){
        return Essay.builder()
                .user(user)
                .recruit(recruit)
                .essayCategory(essayCategory)
                .question(this.question)
                .answer(this.answer)
                .build();
    }

}
