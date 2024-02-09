package com.ssafy.joblog.domain.essay.entity;

import com.ssafy.joblog.domain.essay.dto.request.EssayCategoryUpdateRequestDto;
import com.ssafy.joblog.domain.essay.dto.response.EssayCategoryResponseDto;
import com.ssafy.joblog.domain.user.entity.User;
import com.ssafy.joblog.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class EssayCategory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_category_id")
    private Integer id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ColumnDefault("기타")
    @Column(name = "question_category_name")
    private String questionCategoryName;

    // 생성자
    @Builder
    public EssayCategory(Integer id, User user, String question_category_name) {
        this.id = id;
        this.user = user;
        this.questionCategoryName = question_category_name;
    }

    // update 더티체킹
    public void updateEssayCategory(EssayCategoryUpdateRequestDto essayCategoryUpdateRequestDto) {
        this.questionCategoryName = essayCategoryUpdateRequestDto.getCategoryName();
    }
}
