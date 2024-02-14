package com.ssafy.joblog.domain.essay.entity;

import com.ssafy.joblog.domain.essay.controller.EssayController;
import com.ssafy.joblog.domain.essay.dto.request.EssayUpdateRequestDto;
import com.ssafy.joblog.domain.essay.dto.response.EssayResponseDto;
import com.ssafy.joblog.domain.recruit.entity.Recruit;
import com.ssafy.joblog.domain.user.entity.User;
import com.ssafy.joblog.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Essay extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "essay_id")
    private int id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = LAZY, optional = true)
    @JoinColumn(name = "recruit_id")
    private Recruit recruit;

    @ManyToOne(fetch = LAZY, optional = true)
    @JoinColumn(name = "question_category_id")
    private EssayCategory essayCategory;

    private int question_number;
    @Column(length = 5000)
    private String question;
    @Column(length = 5000)
    private String answer;

    // 생성자
    @Builder
    public Essay(int id, User user, Recruit recruit, EssayCategory essayCategory, int question_number,
                 String question, String answer){
        this.id = id;
        this.user = user;
        this.recruit = recruit;
        this.essayCategory = essayCategory;
        this.question_number = question_number;
        this.question = question;
        this.answer = answer;
    }

    // update 더티체킹
    public void updateEssay(EssayUpdateRequestDto essayUpdateRequestDto, Recruit recruit, EssayCategory essayCategory) {
        this.recruit = recruit;
        this.essayCategory = essayCategory;
        this.question = essayUpdateRequestDto.getQuestion();
        this.answer = essayUpdateRequestDto.getAnswer();
    }

    //Entity to Dto
    public EssayResponseDto toEssayResponseDto() {
        return EssayResponseDto.builder()
                .essayId(id)
                .recruitId(recruit.getId())
                .categoryId(essayCategory.getId())
                .categoryName(essayCategory.getQuestionCategoryName())///////
                .userId(user.getId())
                .question(question)
                .answer(answer)
                .build();
    }
}
