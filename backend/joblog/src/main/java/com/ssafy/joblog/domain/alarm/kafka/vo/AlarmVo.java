package com.ssafy.joblog.domain.alarm.kafka.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlarmVo implements Serializable {

    private String userId;
    private String message;

}
