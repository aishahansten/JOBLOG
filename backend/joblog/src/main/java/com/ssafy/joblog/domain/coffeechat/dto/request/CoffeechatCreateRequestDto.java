package com.ssafy.joblog.domain.coffeechat.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.joblog.domain.coffeechat.entity.CoffeeChatRoom;
import com.ssafy.joblog.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CoffeechatCreateRequestDto {
    private int chatterId;
    private int chatteeId;
    private String consultField;
    private boolean acceptOrNot;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startDate;

    // DTO to Entity
    public CoffeeChatRoom createRoom(User chatter, User chattee){
        return CoffeeChatRoom.builder()
                .chatter(chatter)
                .chattee(chattee)
                .consultField(this.consultField)
                .acceptOrNot(this.acceptOrNot)
                .startDate(this.startDate)
                .build();
    };


}
