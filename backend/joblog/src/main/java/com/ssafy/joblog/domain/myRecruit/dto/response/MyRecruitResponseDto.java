package com.ssafy.joblog.domain.myRecruit.dto.response;

import com.ssafy.joblog.domain.myRecruit.entity.Selection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyRecruitResponseDto {
    private int id;
    private int userId;
    private Long recruitId;
    private boolean scrapOrNot;
    private String title;
    private String companyName;
    private Long companyCode;
    private String job;
    private LocalDateTime openingDate;
    private LocalDateTime expirationDate;
    private String description;
    private List<Selection> selections = new ArrayList<>();
}
