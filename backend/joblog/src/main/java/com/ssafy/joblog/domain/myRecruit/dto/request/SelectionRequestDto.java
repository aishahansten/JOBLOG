package com.ssafy.joblog.domain.myRecruit.dto.request;

import com.ssafy.joblog.domain.myRecruit.entity.MyRecruit;
import com.ssafy.joblog.domain.myRecruit.entity.Selection;
import com.ssafy.joblog.domain.user.entity.User;
import com.ssafy.joblog.global.entity.BaseEntity;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SelectionRequestDto extends BaseEntity {
    private int id;
    private int myRecruitId;
    private int userId;
    private LocalDateTime progressDate;
    private String title;
    private int step;
    private String status;
    private Boolean reviewOrNot;

    public Selection createSelection(User user, MyRecruit myRecruit) {
        return Selection.builder()
                .user(user)
                .myRecruit(myRecruit)
                .progressDate(this.progressDate)
                .companyCode(myRecruit.getCompanyCode() != null ? myRecruit.getCompanyCode(): 0)
                .title(this.title)
                .step(this.step)
                .status(this.status)
                .reviewOrNot(false)
                .build();
    }
}
