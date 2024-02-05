package com.ssafy.joblog.JsonDataReadWrite;

import com.ssafy.joblog.JsonDataReadWrite.dto.requestDto.RecruitRequestDto;
import com.ssafy.joblog.JsonDataReadWrite.entity.Recruit;
import com.ssafy.joblog.JsonDataReadWrite.repository.RecruitRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    private String accesskey = "3arT09ZHgyNKWYkriX0I60XPns9zQoVtXKmgUSbSpkKWym7Lb6";
    WebClient webClient;

    //client(RESTAPI)로부터 Json data 받아오기 + jsonNode로 변환
    public JsonNode getJsonData(int start) {
        DefaultUriBuilderFactory defaultUriBuilderFactory = new DefaultUriBuilderFactory();
        defaultUriBuilderFactory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtFmtMin = DateTimeFormatter.ofPattern("yyyy-MM-dd%20HH:00:00");
        String published_min = now.format(dtFmtMin);
        LocalDateTime result = now.minusHours(4);
        DateTimeFormatter dtFmtMax = DateTimeFormatter.ofPattern("yyyy-MM-dd%20HH:00:00");
        String published_max = result.format(dtFmtMax);
//        System.out.println(now);
//        System.out.println(result);
        System.out.println("hi");

        //webclient 생성
        webClient = WebClient.builder().uriBuilderFactory(defaultUriBuilderFactory).build();
        //webclient를 통해 동기적으로 데이터 요청
//        String responseBody = webClient.get()
//                .uri(builder -> builder
//                        .scheme("https")
//                        .host("oapi.saramin.co.kr")
//                        .path("/job-search")
//                        .queryParam("access-key", accesskey) //.queryParam으로 추가 가능
//                        .queryParam("count", 110)
//                        .queryParam("start", start)
//                        .queryParam("published_min", published_min) //yyyy-mm-dd + "%20" + hh:mm:ss 형식
//                        .queryParam("published_max", published_max)
//                        .build())
//                .retrieve()
//                .bodyToMono(String.class)
//                .block(); // 동기적으로 결과를 얻음
        String responseBody = "{\n" +
                "  \"jobs\": {\n" +
                "    \"count\": 2,\n" +
                "    \"start\": 1,\n" +
                "    \"total\": \"7629\",\n" +
                "    \"job\": [\n" +
                "      {\n" +
                "        \"url\": \"http://www.saramin.co.kr/zf_user/jobs/relay/view?rec_idx=27614114&utm_source=job-search-api&utm_medium=api&utm_campaign=saramin-job-search-api\",\n" +
                "        \"active\": 1,\n" +
                "        \"company\": {\n" +
                "          \"detail\": {\n" +
                "          }\n" +
                "        },\n" +
                "        \"position\": {\n" +
                "          \"title\": \"(주)사람인 사무보조·문서작성 경력 채용합니다11212\",\n" +
                "          \"industry\": {\n" +
                "            \"code\": \"301\",\n" +
                "            \"name\": \"솔루션·SI·ERP·CRM\"\n" +
                "          },\n" +
                "          \"location\": {\n" +
                "            \"code\": \"101050,101060,101070\",\n" +
                "            \"name\": \"서울 > 관악구,서울 > 광진구,서울 > 구로구\"\n" +
                "          },\n" +
                "          \"job-type\": {\n" +
                "            \"code\": \"1\",\n" +
                "            \"name\": \"정규직\"\n" +
                "          },\n" +
                "          \"job-mid-code\": {\n" +
                "            \"code\": \"22\",\n" +
                "            \"name\": \"건설·건축\"\n" +
                "          },\n" +
                "          \"job-code\": {\n" +
                "            \"code\": \"2206\",\n" +
                "            \"name\": \"건축설계\"\n" +
                "          },\n" +
                "          \"experience-level\": {\n" +
                "            \"code\": 2,\n" +
                "            \"min\": 2,\n" +
                "            \"max\": 3,\n" +
                "            \"name\": \"경력 2~3년\"\n" +
                "          },\n" +
                "          \"required-education-level\": {\n" +
                "            \"code\": \"0\",\n" +
                "            \"name\": \"학력무관\"\n" +
                "          }\n" +
                "        },\n" +
                "        \"keyword\": \"SI·시스템통합,Excel·도표,PowerPoint,전산입력·편집\",\n" +
                "        \"salary\": {\n" +
                "          \"code\": \"6\",\n" +
                "          \"name\": \"1,800~2,000만원\"\n" +
                "        },\n" +
                "        \"id\": \"27614114\",\n" +
                "        \"posting-timestamp\": \"1559191564\",\n" +
                "        \"posting-date\": \"2019-05-30T13:46:04+0900\",\n" +
                "        \"modification-timestamp\": \"1559191564\",\n" +
                "        \"opening-timestamp\": \"1559188800\",\n" +
                "        \"expiration-timestamp\": \"1561820399\",\n" +
                "        \"expiration-date\": \"2019-06-29T23:59:59+0900\",\n" +
                "        \"close-type\": {\n" +
                "          \"code\": \"1\",\n" +
                "          \"name\": \"접수마감일\"\n" +
                "        },\n" +
                "        \"read-cnt\": \"0\",\n" +
                "        \"apply-cnt\": \"0\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";
        // jsonNode로 변환
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
        // 처음 100개 불러오기
        int total = 0;
        JsonNode jsonNode = getJsonData(0);
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
            jsonNode = getJsonData(start);
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
