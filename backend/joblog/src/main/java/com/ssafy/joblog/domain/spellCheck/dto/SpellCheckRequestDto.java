package com.ssafy.joblog.domain.spellCheck.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class SpellCheckRequestDto {
    private String text;
}