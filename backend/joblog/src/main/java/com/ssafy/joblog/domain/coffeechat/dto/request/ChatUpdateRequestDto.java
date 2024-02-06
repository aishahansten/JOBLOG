package com.ssafy.joblog.domain.coffeechat.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChatUpdateRequestDto {
    private int chatId;
    private boolean acceptOrNot;
}
