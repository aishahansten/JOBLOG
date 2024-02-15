package com.ssafy.joblog.domain.spellCheck.service;

import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SpellCheckService {
    public String fixSpell(String body) throws JSONException {
        // 추출된 JavaScript 데이터 처리
        int idx = body.indexOf("data =");
        body = body.substring(idx + 7);

        idx = body.indexOf("}];");
        body = body.substring(0, idx + 2);

        // JSON 배열로 변환
        JSONArray jsonArray = new JSONArray(body);
        String originalText = "";

        // 배열 순회
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            originalText = jsonObject.getString("str");

            JSONArray errInfoArray = jsonObject.getJSONArray("errInfo");

            // errInfo 배열 순회
            for (int j = errInfoArray.length() - 1; j >= 0; j--) {
                JSONObject errorObj = errInfoArray.getJSONObject(j);
                int start = errorObj.getInt("start");
                int end = errorObj.getInt("end");
                String orgStr = errorObj.getString("orgStr");
                String candWord = errorObj.getString("candWord");

                // orgStr을 candWord로 대체
                originalText = originalText.substring(0, start) + "<u>" + candWord + "</u>" + originalText.substring(end);
            }
        }
        return originalText;
    }
}
