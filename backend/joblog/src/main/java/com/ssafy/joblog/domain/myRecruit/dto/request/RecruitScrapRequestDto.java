package com.ssafy.joblog.domain.myRecruit.dto.request;

import com.ssafy.joblog.domain.myRecruit.entity.MyRecruit;
import com.ssafy.joblog.domain.recruit.entity.Recruit;
import com.ssafy.joblog.domain.user.entity.Follow;
import com.ssafy.joblog.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RecruitScrapRequestDto {
    private int userId;
    private Long recruitId;

    public MyRecruit createScrap(User user, Recruit recruit) {
        return MyRecruit.builder()
                .user(user)
                .recruit(recruit)
                .scrapOrNot(true)
                .title(recruit.getTitle())
                .companyName(recruit.getCompanyName())
                .companyCode(recruit.getCompanyCode())
                .openingDate(LocalDateTime.ofInstant(Instant.ofEpochSecond(recruit.getOpeningTimestamp()), ZoneId.systemDefault()))
                .expirationDate(recruit.getExpirationDate())
                .build();
    }
}
