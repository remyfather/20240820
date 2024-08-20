package com.remy.chatapi.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class SolarService {
    @Value("${solar.api.url}")
    private String apiUrl;

    @Value("${solar.api.key}")
    private String apiKey;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public String sendMessage(String userMessage) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("Content-Type", "application/json");

        // JSON 요청 본문을 생성합니다.
        Map<String, Object> jsonBody = new HashMap<>();
        jsonBody.put("model", "solar-1-mini-chat");
        jsonBody.put("messages", new Object[] {
                new HashMap<String, String>() {{
                    put("role", "system");
                    put("content", "You are a helpful assistant.");
                }},
                new HashMap<String, String>() {{
                    put("role", "user");
                    put("content", userMessage);
                }}
        });
        jsonBody.put("stream", false);

        try {
            // JSON 문자열로 변환합니다.
            String body = objectMapper.writeValueAsString(jsonBody);

            HttpEntity<String> entity = new HttpEntity<>(body, headers);
            ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, String.class);

            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("Failed to send message to Solar API", e);
        }
    }

}
