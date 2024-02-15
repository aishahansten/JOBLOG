package com.ssafy.joblog.domain.spellCheck.controller;

import com.ssafy.joblog.domain.spellCheck.dto.SpellCheckRequestDto;
import com.ssafy.joblog.domain.spellCheck.service.SpellCheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SpellCheckController {

    private final SpellCheckService spellCheckService;
    @PostMapping("/check-spelling")
    public ResponseEntity<String> checkSpelling(@RequestBody SpellCheckRequestDto text) throws JSONException {
        String url = "http://speller.cs.pusan.ac.kr/results";

        // Form data
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("text1", text.getText());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(formData, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);

        return new ResponseEntity<>(spellCheckService.fixSpell(responseEntity.getBody()), HttpStatus.OK);
    }

}
