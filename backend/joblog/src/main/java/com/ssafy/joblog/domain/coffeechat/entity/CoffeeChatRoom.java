package com.ssafy.joblog.domain.coffeechat.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.joblog.domain.coffeechat.dto.request.ChatUpdateRequestDto;
import com.ssafy.joblog.domain.user.entity.User;
import com.ssafy.joblog.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Where;


import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CoffeeChatRoom extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_room_id")
    private int id;

    //연관관계 주인(=외래키 갖고있음)
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="chatter_id")
    private User chatter;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="chattee_id")
    private User chattee;

    @Column(name = "consult_field")
    private String consultField;

    @ColumnDefault("false")
    @Column(name = "accept_or_not", nullable = false)
    private boolean acceptOrNot;

    // 기본값 null, 신청 시 start_date 생성됨
    @Column(name = "start_date", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startDate;

    // 생성자
    @Builder
    public CoffeeChatRoom(int id, User chatter, User chattee, String consultField, boolean acceptOrNot, LocalDateTime startDate) {
        this.id = id;
        this.chatter = chatter;
        this.chattee = chattee;
        this.consultField = consultField;
        this.acceptOrNot = acceptOrNot;
        this.startDate = startDate;
    }
}

