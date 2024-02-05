package com.ssafy.joblog.domain.myRecruit.dto.request;

import com.ssafy.joblog.domain.myRecruit.entity.MyRecruit;
import com.ssafy.joblog.domain.user.entity.User;
import com.ssafy.joblog.global.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MyRecruitRequestDto extends BaseEntity {
    private int id;
    private int userId;
    private String title;
    private String companyName;
    private String job;
    private LocalDateTime openingDate;
    private LocalDateTime expirationDate;
    private String description;

    public MyRecruit createMyRecruit(User user) {
        return MyRecruit.builder()
                .user(user)
                .scrapOrNot(false)
                .title(this.title)
                .companyName(this.companyName)
                .job(this.job)
                .openingDate(this.openingDate)
                .expirationDate(this.expirationDate)
                .description(this.description)
                .build();
    }
}
