package com.ssafy.joblog.domain.alarm.kafka.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.joblog.domain.alarm.kafka.vo.AlarmVo;
import com.ssafy.joblog.domain.coffeechat.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public ProducerService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public String createMessage(int userId, String msg) {
        System.out.println("producer service가 알림 만드는 중 : " + userId + " " + msg);
        AlarmVo alarm = new AlarmVo(String.valueOf(userId), msg);
        ObjectMapper mapper = new ObjectMapper();
        String message = "";
        try {
            message = mapper.writeValueAsString(alarm);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return message;
    }

    public void chatCreateAlarm(int userId, String message) {
        kafkaTemplate.send("chat", createMessage(userId, message));
    }

    public void chatAcceptAlarm(int userId, String message) {
        kafkaTemplate.send("chat", createMessage(userId, message));
    }

    public void chatRejectAlarm(int userId, String message) {
        kafkaTemplate.send("chat", createMessage(userId, message));
    }

    public void upcomingScheduleAlarm(int userId, String message) {
        kafkaTemplate.send("schedule", createMessage(userId, message));
    }

}
