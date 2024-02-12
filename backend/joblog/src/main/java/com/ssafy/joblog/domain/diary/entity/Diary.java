package com.ssafy.joblog.domain.diary.entity;

import com.ssafy.joblog.domain.diary.dto.request.DiaryUpdateRequestDto;
import com.ssafy.joblog.domain.diary.dto.response.DiaryResponseDto;
import com.ssafy.joblog.domain.user.entity.User;
import com.ssafy.joblog.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Diary extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diary_id")
    private int id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String content;

    // 더티 체킹을 통한 update
    public void updateDiary(DiaryUpdateRequestDto diaryUpdateRequestDto) {
        this.content = diaryUpdateRequestDto.getContent();
    }

    // 생성자
    @Builder
    public Diary(int id, User user, String content) {
        this.id = id;
        this.user = user;
        this.content = content;
    }

    // Entity to Dto 전체조회, 상세조회
    public DiaryResponseDto toDiaryResponseDto() {
        return DiaryResponseDto.builder()
                .diaryId(id)
                .userId(user.getId())
                .content(content)
                .createdDate(getCreatedDate())
                .modifiedDate(getModifiedDate())
                .build();
    }
}