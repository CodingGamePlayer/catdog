package kr.co.catdog.apicontroller;

import kr.co.catdog.dto.ChatGptResponseDto;
import kr.co.catdog.dto.HospitalDTO;
import kr.co.catdog.dto.QuestionRequestDto;
import kr.co.catdog.service.ChatGptService;
import kr.co.catdog.service.HospitalService;
import kr.co.catdog.service.NamingService;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class NamingApiController {
    private NamingService namingService;
    private ChatGptService chatGptService;

    @PostMapping("/api/user/naming/mypetName")
    public ChatGptResponseDto sendQuestion(@RequestBody QuestionRequestDto requestDto) {
        log.info(chatGptService.askQuestion(requestDto)+"");
        return chatGptService.askQuestion(requestDto);
    }

    public NamingApiController(NamingService namingService, ChatGptService chatGptService) {
        this.namingService = namingService;
        this.chatGptService = chatGptService;
    }
}
