package com.ssafy.joblog.domain.coffeechat.service;

import com.ssafy.joblog.domain.coffeechat.dto.request.CoffeechatCreateRequestDto;
import com.ssafy.joblog.domain.coffeechat.entity.CoffeeChatRoom;
import com.ssafy.joblog.domain.coffeechat.repository.CoffeechatRepository;
import com.ssafy.joblog.domain.user.entity.User;
import com.ssafy.joblog.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class CoffeechatService {

    private final UserRepository userRepository;
    private final CoffeechatRepository coffeechatRepository;

    // 1. 커피챗 신청하기
    public void createRoom(CoffeechatCreateRequestDto coffeechatCreateRequestDto){
        User chatter = userRepository.findById(coffeechatCreateRequestDto.getChatterId())
                .orElseThrow(()-> new IllegalArgumentException("채터가 존재하지 않습니다"));
        User chattee = userRepository.findById(coffeechatCreateRequestDto.getChatteeId())
                .orElseThrow(() -> new IllegalArgumentException("채티가 존재하지 않습니다"));
        CoffeeChatRoom coffeeChatRoom = coffeechatCreateRequestDto.createRoom(chatter, chattee);
        coffeechatRepository.save(coffeeChatRoom);

    }


}
