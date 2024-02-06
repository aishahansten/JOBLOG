package com.ssafy.joblog.domain.myRecruit.dto.request;

import com.ssafy.joblog.domain.myRecruit.entity.MyRecruit;
import com.ssafy.joblog.domain.myRecruit.entity.Selection;
import com.ssafy.joblog.domain.user.entity.User;
import com.ssafy.joblog.global.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequestDto extends BaseEntity {
    private int selectionId;
    private String type;
    private Integer totalQuestion;
    private Integer timeRequired;
    private String applyJob;
    private int difficulty;
    private int atmosphere;
    private String comment;
    private String review; //private
}
