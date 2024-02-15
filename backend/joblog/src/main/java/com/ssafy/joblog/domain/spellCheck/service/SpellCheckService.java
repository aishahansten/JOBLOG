package com.ssafy.joblog.domain.spellCheck.service;

import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SpellCheckService {
    public String fixSpell(String body, String original) throws JSONException {
        // 추출된 JavaScript 데이터 처리
        int idx = body.indexOf("data =");
        if(idx == -1){
            return original;
        }
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
                originalText = originalText.substring(0, start) + "<b><i>" + candWord + "</i></b>" + originalText.substring(end);
            }
        }
        return originalText;
    }
}
