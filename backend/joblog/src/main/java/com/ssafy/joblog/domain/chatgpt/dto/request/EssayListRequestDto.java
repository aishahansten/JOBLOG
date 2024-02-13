package com.ssafy.joblog.domain.chatgpt.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EssayListRequestDto {
    private List<Integer> essayList = new ArrayList<>();
}
