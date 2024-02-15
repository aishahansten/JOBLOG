package com.ssafy.joblog.domain.chatter.dto.request;

import com.ssafy.joblog.domain.chatter.entity.ChatterProfile;
import com.ssafy.joblog.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChatterCreateRequestDto {
    private int id;
    private int userId;
    private String job;
    private String career;
    private String description;

    // DTO to Entity
    public ChatterProfile createChatter(User user) {
        return ChatterProfile.builder()
                .id(this.id)
                .user(user)
                .job(this.job)
                .career(this.career)
                .description(this.description)
                .build();
    }

}
