package com.ssafy.joblog.domain.diary.dto.request;

import com.ssafy.joblog.domain.diary.entity.Diary;
import com.ssafy.joblog.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DiaryCreateRequestDto {
    private int userId;
    private String content;

    //DTO to Entity
    public Diary createDiary(User user){
        return Diary.builder()
                .user(user)
                .content(this.content)
                .build();
    }
}


