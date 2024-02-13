package com.ssafy.joblog.domain.chatgpt.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.joblog.domain.chatgpt.config.ChatGptConfig;
import com.ssafy.joblog.domain.chatgpt.dto.request.ChatGptRequestDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.ssafy.joblog.domain.chatgpt.dto.request.EssayListRequestDto;
import com.ssafy.joblog.domain.chatgpt.dto.response.Choice;
import com.ssafy.joblog.domain.essay.entity.Essay;
import com.ssafy.joblog.domain.essay.repository.EssayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class InterviewService {
    private final ChatGptConfig chatGptConfig;
    private final EssayRepository essayRepository;

    @Value("${openai.url.legacy-prompt}")
    private String legacyPromptUrl;

    public String createPrompt(EssayListRequestDto essayListRequestDto) {
        ChatGptRequestDto chatGptRequestDto = ChatGptRequestDto.builder()
                .prompt(createEssayString(essayListRequestDto))
                .build();

        HttpHeaders headers = chatGptConfig.httpHeaders();

        HttpEntity<ChatGptRequestDto> requestEntity = new HttpEntity<>(chatGptRequestDto, headers);
        ResponseEntity<String> response = chatGptConfig
                .restTemplate()
                .exchange(legacyPromptUrl, HttpMethod.POST, requestEntity, String.class);

        Map<String, Object> resultMap = new HashMap<>();
        try {
            ObjectMapper om = new ObjectMapper();
            resultMap = om.readValue(response.getBody(), new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        List<Choice> choices = (List<Choice>) resultMap.get("choices");
        Map<String, Object> textMap = (Map<String, Object>) choices.get(0);
        return (String) textMap.get("text");
    }


    public String createEssayString(EssayListRequestDto essayListRequestDto) {
        String result = "";
        List<Integer> essayList = essayListRequestDto.getEssayList();
        int size = essayList.size();
        System.out.println(size);
        for (int i = 1; i <= size; i++) {
            int idx = essayList.get(i - 1);
            Essay essay = essayRepository.findByIdAndIsDeleteIsFalse(idx)
                    .orElseThrow(() -> new IllegalArgumentException("해당 자소서가 존재하지 않습니다"));
            String question = essay.getQuestion();
            String answer = essay.getAnswer();
            result += i + "번 질문: " + question + "\n"
                    + i + "번 답변: " + answer + "\n";
        }
        result += "답변을 기반으로 각 번호별 면접 예상질문을 " + size + "개만 생성해주세요.";
        return result;
    }
}