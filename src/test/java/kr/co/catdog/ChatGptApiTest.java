package kr.co.catdog;


import kr.co.catdog.dto.Choice;
import kr.co.catdog.dto.QuestionRequestDto;
import kr.co.catdog.service.ChatGptService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;

import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Slf4j
public class ChatGptApiTest {


    @Value("${chatgpt.api.key}")
    private String apiKey;
    private static final String ENDPOINT = "https://api.openai.com/v1/completions";

    @Autowired
    private ChatGptService chatGptService;

    @Test
    @DisplayName("chatGPT api test")
    public void callApi() throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model","text-davinci-003");
        requestBody.put("prompt", "강아지 이름 3개 지어주는데 [발랄함, 검은색] 키워드를 반영해서 만들어주고 이유도 알려주고 이름 : 이유 | 형식으로 부탁해");
        requestBody.put("temperature", 0.9f);
        requestBody.put("max_tokens", 800);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response = restTemplate.postForEntity(ENDPOINT, requestEntity, Map.class);
        //log.info(response.getBody().toString());
        //List<Choice> answer = response.getBody().get("choices");
        log.info(response.getBody().get("choices").toString());
    }

    @Test
    @DisplayName("chatGPT api test")
    public void askQuestion() throws Exception {

    }
}
