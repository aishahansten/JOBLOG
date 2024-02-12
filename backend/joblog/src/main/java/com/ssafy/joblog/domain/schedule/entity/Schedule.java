package com.ssafy.joblog.domain.schedule.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.joblog.domain.schedule.dto.request.ScheduleUpdateRequestDto;
import com.ssafy.joblog.domain.schedule.dto.response.ScheduleResponseDto;
import com.ssafy.joblog.domain.user.entity.User;
import com.ssafy.joblog.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Schedule extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private int id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String title;
    private String content;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startDate;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mmx")
    private LocalDateTime endDate;

    //더티 체킹 : for UPDATE
    public void updateSchedule(ScheduleUpdateRequestDto scheduleUpdateRequestDto) {
        this.title = scheduleUpdateRequestDto.getTitle();
        this.content = scheduleUpdateRequestDto.getContent();
        this.startDate = scheduleUpdateRequestDto.getStartDate();
        this.endDate = scheduleUpdateRequestDto.getEndDate();
    }

    // 생성자
    @Builder
    public Schedule(int id, User user, String title, String content, LocalDateTime startDate, LocalDateTime endDate) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Entity -> DTO 개인 전체 조회하기, DTO 개인 일정 상세 조회하기
    public ScheduleResponseDto toScheduleResponseDto() {
        return ScheduleResponseDto.builder()
                .scheduleId(id)
                .userId(user.getId())
                .title(title)
                .content(content)
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }
}
