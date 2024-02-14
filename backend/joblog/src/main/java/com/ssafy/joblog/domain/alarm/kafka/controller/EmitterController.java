package com.ssafy.joblog.domain.alarm.kafka.controller;

import com.ssafy.joblog.domain.alarm.kafka.service.ConsumerService;
import com.ssafy.joblog.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class EmitterController {

    private final ConsumerService consumerService;
    public static final Long DEFAULT_TIMEOUT = 3600L * 1000;

    @GetMapping(value = "/api/connect", produces = "text/event-stream")
    public SseEmitter stream(Authentication authentication, @RequestHeader(value = "Last-Event-Id", required = false, defaultValue = "") String lastEventId) throws IOException {
        User user = (User) authentication.getPrincipal();
        return consumerService.addEmitter(String.valueOf(user.getId()), lastEventId);
    }

}
