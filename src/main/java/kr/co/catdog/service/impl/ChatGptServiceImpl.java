package kr.co.catdog.service.impl;

import kr.co.catdog.config.ChatGptConfig;
import kr.co.catdog.dto.ChatGptRequestDto;
import kr.co.catdog.dto.ChatGptResponseDto;
import kr.co.catdog.dto.QuestionRequestDto;
import kr.co.catdog.service.ChatGptService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ChatGptServiceImpl implements ChatGptService {

    @Value("${chatgpt.api.key}")
    private String apiKey;
    private static RestTemplate restTemplate = new RestTemplate();

    public HttpEntity<ChatGptRequestDto> buildHttpEntity(ChatGptRequestDto requestDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(ChatGptConfig.MEDIA_TYPE));
        headers.add(ChatGptConfig.AUTHORIZATION, ChatGptConfig.BEARER + apiKey);
        return new HttpEntity<>(requestDto, headers);
    }

    public ChatGptResponseDto getResponse(HttpEntity<ChatGptRequestDto> chatGptRequestDtoHttpEntity) {
        ResponseEntity<ChatGptResponseDto> responseEntity = restTemplate.postForEntity(
                ChatGptConfig.URL,
                chatGptRequestDtoHttpEntity,
                ChatGptResponseDto.class);

        return responseEntity.getBody();
    }

    public ChatGptResponseDto askQuestion(QuestionRequestDto requestDto) {
        return this.getResponse(
                this.buildHttpEntity(
                        new ChatGptRequestDto(
                                ChatGptConfig.MODEL,
                                "강아지 이름 3개 지어주는데 [발랄함, 검은색] 키워드를 반영해서 만들어주고 이유도 알려주고 이름 : 이유 | 형식으로 부탁해.",
                                ChatGptConfig.MAX_TOKEN,
                                ChatGptConfig.TEMPERATURE,
                                ChatGptConfig.TOP_P
                        )
                )
        );
    }
}
