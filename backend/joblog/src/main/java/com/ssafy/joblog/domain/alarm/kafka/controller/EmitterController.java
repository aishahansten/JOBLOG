package com.ssafy.joblog.domain.alarm.kafka.controller;

import com.ssafy.joblog.domain.alarm.kafka.service.ConsumerService;
import com.ssafy.joblog.domain.user.entity.User;
import com.ssafy.joblog.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class EmitterController {

    private final ConsumerService consumerService;
    private final UserRepository userRepository;
    public static final Long DEFAULT_TIMEOUT = 3600L * 1000;

    @GetMapping(value = "/api/connect/{userId}", produces = "text/event-stream")
    public SseEmitter stream(@PathVariable(value = "userId") int userId, @RequestHeader(value = "Last-Event-Id", required = false, defaultValue = "") String lastEventId) throws IOException {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다"));
        return consumerService.addEmitter(String.valueOf(user.getId()), lastEventId);
    }

}
