package com.ssafy.joblog.JsonDataReadWrite;

import com.ssafy.joblog.JsonDataReadWrite.dto.requestDto.RecruitRequestDto;
import com.ssafy.joblog.JsonDataReadWrite.entity.Recruit;
import com.ssafy.joblog.JsonDataReadWrite.repository.RecruitRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class JsonDataUtils {
    @Autowired
    private RecruitRepository recruitRepository;

    @Value("${spring.batch.access-key.access-key}")
    private String accesskey;
    WebClient webClient;

    //client(RESTAPI)로부터 Json data 받아오기 + jsonNode로 변환
    public JsonNode getJsonData(int start, String published_min, String published_max) {
        DefaultUriBuilderFactory defaultUriBuilderFactory = new DefaultUriBuilderFactory();
        defaultUriBuilderFactory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);

        //webclient 생성
        webClient = WebClient.builder().uriBuilderFactory(defaultUriBuilderFactory).build();
        //webclient를 통해 동기적으로 데이터 요청
        String responseBody = webClient.get()
                .uri(builder -> builder
                        .scheme("https")
                        .host("oapi.saramin.co.kr")
                        .path("/job-search")
                        .queryParam("access-key", accesskey) //.queryParam으로 추가 가능
                        .queryParam("count", 110)
                        .queryParam("start", start)
                        .queryParam("published_min", published_min) //yyyy-mm-dd+"%20"+hh:mm:ss 형식
                        .queryParam("published_max", published_max)
                        .queryParam("job_type", 1)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block(); // 동기적으로 결과를 얻음
        return parseJson(responseBody);
    }

    public JsonNode parseJson(String responseBody) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readTree(responseBody);
        } catch (IOException e) {
            // 예외 처리 필요
            e.printStackTrace();
            return null;
        }
    }

    public List<RecruitRequestDto> getJsonDataAsDtoList() throws IOException {
        List<RecruitRequestDto> recruitRequestDtoList = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtFmtMin = DateTimeFormatter.ofPattern("yyyy-MM-dd%20HH:00:00");
        LocalDateTime result = now.minusHours(3);
        String published_min = result.format(dtFmtMin);
        DateTimeFormatter dtFmtMax = DateTimeFormatter.ofPattern("yyyy-MM-dd%20HH:59:59");
        result = now.minusHours(1);
        String published_max = result.format(dtFmtMax);

        //처음 110개 불러오기
        int total = 0;
        JsonNode jsonNode = getJsonData(0, published_min, published_max);
        if (jsonNode != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNodeTotal = jsonNode.get("jobs").get("total");
            total = objectMapper.readValue(jsonNodeTotal.toString(), Integer.class);

            jsonNode = jsonNode.get("jobs").get("job");
            String json = jsonNode.toString();
            recruitRequestDtoList.addAll(Arrays.asList(objectMapper.readValue(json, RecruitRequestDto[].class)));
        }
        // 더 불러올 데이터 있는지, 있다면 for문
        for (int start = 1; start <= total / 110; start++) {
            jsonNode = getJsonData(start, published_min, published_max);
            if (jsonNode != null) {
                jsonNode = jsonNode.get("jobs").get("job");
                String json = jsonNode.toString();
                ObjectMapper objectMapper = new ObjectMapper();
                recruitRequestDtoList.addAll(Arrays.asList(objectMapper.readValue(json, RecruitRequestDto[].class)));
            }
        }

        //중복 채용공고 거르기
        int size = recruitRequestDtoList.size();
        for (int i = size - 1; i >= 0; i--) {
            Long jobId = Long.valueOf(recruitRequestDtoList.get(i).getId());
            Optional<Recruit> recruit = recruitRepository.findByJobId(jobId);
            if (recruit.isPresent()) {
                recruitRequestDtoList.remove(i);
            }
        }

        return recruitRequestDtoList;
    }

}
