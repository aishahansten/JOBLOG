package com.ssafy.joblog.domain.alarm.entity;

import com.ssafy.joblog.domain.coffeechat.entity.CoffeeChatRoom;
import com.ssafy.joblog.domain.myRecruit.entity.MyRecruit;
import com.ssafy.joblog.domain.myRecruit.entity.Selection;
import com.ssafy.joblog.domain.schedule.entity.Schedule;
import com.ssafy.joblog.domain.user.entity.User;
import com.ssafy.joblog.global.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
public class Alarm extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alarm_id")
    private int id;
    @NotNull
    private int type;
    private String code;

    //연관관계 주인(=외래키 갖고있음)
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    //연관관계 주인(=외래키 갖고있음)
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "my_recruit_id")
    private MyRecruit myRecruit;

    //연관관계 주인(=외래키 갖고있음)
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    //연관관계 주인(=외래키 갖고있음)
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "chat_room_id")
    private CoffeeChatRoom coffeeChatRoom;

    //연관관계 주인(=외래키 갖고있음)
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "selection_id")
    private Selection selection;

    @ColumnDefault("0")
    private boolean isChecked;

    @Builder
    public Alarm(int id, int type, String code, User user, MyRecruit myRecruit, Schedule schedule, CoffeeChatRoom coffeeChatRoom, Selection selection) {
        this.id = id;
        this.type = type;
        this.code = code;
        this.user = user;
        this.myRecruit = myRecruit;
        this.schedule = schedule;
        this.coffeeChatRoom = coffeeChatRoom;
        this.selection = selection;
    }

}
