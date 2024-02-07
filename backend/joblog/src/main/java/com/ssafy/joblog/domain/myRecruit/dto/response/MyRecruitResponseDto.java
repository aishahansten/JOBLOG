package com.ssafy.joblog.domain.myRecruit.dto.response;

import com.ssafy.joblog.domain.myRecruit.entity.MyRecruit;
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
    private List<SelectionListResponseDto> selectionListResponseDto = new ArrayList<>();

    public static MyRecruitResponseDto fromEntity(MyRecruit myRecruit) {
        return MyRecruitResponseDto.builder()
                .id(myRecruit.getId())
                .userId(myRecruit.getUser().getId())
                .recruitId(myRecruit.getRecruit() != null ? myRecruit.getRecruit().getId() : null)
                .scrapOrNot(myRecruit.isScrapOrNot())
                .title(myRecruit.getTitle())
                .companyName(myRecruit.getCompanyName() != null ? myRecruit.getCompanyName() : null)
                .companyCode(myRecruit.getCompanyCode() != null ? myRecruit.getCompanyCode() : null)
                .job(myRecruit.getJob())
                .openingDate(myRecruit.getOpeningDate() != null ? myRecruit.getOpeningDate() : null)
                .expirationDate(myRecruit.getExpirationDate() != null ? myRecruit.getExpirationDate() : null)
                .description(myRecruit.getDescription())
                .build();
    }

    public static MyRecruitResponseDto fromEntityList(MyRecruit myRecruit, List<SelectionListResponseDto> selectionListResponseDto) {
        return MyRecruitResponseDto.builder()
                .id(myRecruit.getId())
                .userId(myRecruit.getUser().getId())
                .recruitId(myRecruit.getRecruit() != null ? myRecruit.getRecruit().getId() : null)
                .scrapOrNot(myRecruit.isScrapOrNot())
                .title(myRecruit.getTitle())
                .companyName(myRecruit.getCompanyName() != null ? myRecruit.getCompanyName() : null)
                .companyCode(myRecruit.getCompanyCode() != null ? myRecruit.getCompanyCode() : null)
                .job(myRecruit.getJob())
                .openingDate(myRecruit.getOpeningDate() != null ? myRecruit.getOpeningDate() : null)
                .expirationDate(myRecruit.getExpirationDate() != null ? myRecruit.getExpirationDate() : null)
                .description(myRecruit.getDescription())
                .selectionListResponseDto(selectionListResponseDto)
                .build();
    }
}
