package com.ssafy.joblog.domain.coffeechat.service;

import com.ssafy.joblog.domain.alarm.kafka.service.ProducerService;
import com.ssafy.joblog.domain.alarm.service.AlarmService;
import com.ssafy.joblog.domain.coffeechat.dto.request.ChatCreateRequestDto;
import com.ssafy.joblog.domain.coffeechat.dto.request.ChatUpdateRequestDto;
import com.ssafy.joblog.domain.coffeechat.dto.response.ChatResponseDto;
import com.ssafy.joblog.domain.coffeechat.entity.CoffeeChatRoom;
import com.ssafy.joblog.domain.coffeechat.repository.ChatRepository;
import com.ssafy.joblog.domain.user.entity.User;
import com.ssafy.joblog.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class ChatService {

    private final UserRepository userRepository;
    private final ChatRepository chatRepository;
    private final AlarmService alarmService;

    // 1. 커피챗 신청하기
    public void createChat(ChatCreateRequestDto chatCreateRequestDto){
        User chatter = userRepository.findById(chatCreateRequestDto.getChatterId())
                .orElseThrow(()-> new IllegalArgumentException("채터가 존재하지 않습니다"));
        User chattee = userRepository.findById(chatCreateRequestDto.getChatteeId())
                .orElseThrow(() -> new IllegalArgumentException("채티가 존재하지 않습니다"));
        CoffeeChatRoom coffeeChatRoom = chatCreateRequestDto.createRoom(chatter, chattee);
        int chatId = chatRepository.save(coffeeChatRoom).getId();
        alarmService.sendChatCreateAlarm(chatter.getId(), chattee.getId(), chatId);
    }

    // 2. 커피챗 조회하기
    @Transactional(readOnly = true)
    public List<ChatResponseDto> getChatsByUserId(Integer userId) {
        List<CoffeeChatRoom> chattings = chatRepository.findByChatterIdAndIsDeleteIsFalseOrChatteeIdAndIsDeleteIsFalse(userId, userId);

        List<ChatResponseDto> getChatsList = new ArrayList<>();
        chattings.forEach(chat -> getChatsList.add(ChatResponseDto.builder()
                .chatId(chat.getId())
                .chatterId(chat.getChatter().getId())
                .chatteeId(chat.getChattee().getId())
                .title(chat.getTitle())
                .consultField(chat.getConsultField())
                .acceptOrNot(chat.isAcceptOrNot())
                .startDate(chat.getStartDate())
                .build()));
        return getChatsList;
    }

    // 3. 커피챗 수락하기
    @Transactional
    public void markAcceptChat(int chatId) {
        CoffeeChatRoom coffeeChatRoom = chatRepository.findById(chatId)
                .orElseThrow(() -> new IllegalArgumentException("해당 커피챗이 존재하지 않습니다"));
        chatRepository.markAcceptChat(chatId);
        chatRepository.markShowChat(chatId);
        alarmService.sendChatAcceptAlarm(coffeeChatRoom.getChattee().getId(), chatId);
    }

    // 4. 커피챗 거절하기
    @Transactional
    public void markBanChat(int chatId) {
        CoffeeChatRoom coffeeChatRoom = chatRepository.findById(chatId)
                .orElseThrow(() -> new IllegalArgumentException("해당 커피챗이 존재하지 않습니다"));
        chatRepository.markUnacceptChat(chatId);
        chatRepository.markNoshowChat(chatId);
        alarmService.sendChatRejectAlarm(coffeeChatRoom.getChattee().getId(), chatId);
    }
}
