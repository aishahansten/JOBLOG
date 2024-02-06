package com.ssafy.joblog.domain.alarm.entity;

import com.ssafy.joblog.domain.schedule.entity.Schedule;
import com.ssafy.joblog.domain.user.entity.User;
import com.ssafy.joblog.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Alarm extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alarm_id")
    private int id;

    //연관관계 주인(=외래키 갖고있음)
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    //연관관계 주인(=외래키 갖고있음)
//    @ManyToOne(fetch = LAZY)
//    @JoinColumn(name = "user_recruit_id")
//    private UserRecruit user_recruit;

    //연관관계 주인(=외래키 갖고있음)
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    //연관관계 주인(=외래키 갖고있음)
//    @ManyToOne(fetch = LAZY)
//    @JoinColumn(name = "chat_room_id")
//    private CoffeeChatRoom coffeeChatRoom;

    //연관관계 주인(=외래키 갖고있음)
//    @ManyToOne(fetch = LAZY)
//    @JoinColumn(name = "apply_id")
//    private ApplyStatus applyStatus;

    @ColumnDefault("0")
    private boolean isChecked;

    @Builder
    public Alarm(int id, User user, Schedule schedule) {
        this.id = id;
        this.user = user;
        this.schedule = schedule;
    }

}
