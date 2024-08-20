package com.remy.chatapi.controller;

import com.remy.chatapi.service.OpenAIService;
import com.remy.chatapi.service.SolarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final OpenAIService openAIService;
    private final SolarService solarService;

    @PostMapping("/askOpenAi")
    public ResponseEntity<String> chatWithOpenAI(@RequestBody String message){
        System.out.println("input data -> "+message);
        String response = openAIService.sendMessage(message);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/askSolar")
    public ResponseEntity<String> chatWithSolar(@RequestBody String message) {
        String response = solarService.sendMessage(message);
        return ResponseEntity.ok(response);
    }
}
