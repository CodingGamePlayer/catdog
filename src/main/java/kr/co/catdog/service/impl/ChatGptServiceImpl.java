package kr.co.catdog.service.impl;

import kr.co.catdog.config.ChatGptConfig;
import kr.co.catdog.dto.ChatGptRequestDto;
import kr.co.catdog.dto.ChatGptResponseDto;
import kr.co.catdog.dto.QuestionRequestDto;
import kr.co.catdog.service.ChatGptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
@Slf4j
public class ChatGptServiceImpl implements ChatGptService {

    @Value("${chatgpt.api.key}")
    private String apiKey;
    //private static RestTemplate restTemplate = new RestTemplate();

    public HttpEntity<ChatGptRequestDto> buildHttpEntity(ChatGptRequestDto requestDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(ChatGptConfig.MEDIA_TYPE));
        headers.add(ChatGptConfig.AUTHORIZATION, ChatGptConfig.BEARER + apiKey);
        return new HttpEntity<>(requestDto, headers);
    }

    public ChatGptResponseDto getResponse(HttpEntity<ChatGptRequestDto> chatGptRequestDtoHttpEntity) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            public boolean hasError(ClientHttpResponse response) throws IOException {
                HttpStatus statusCode = response.getStatusCode();
                return statusCode.series() == HttpStatus.Series.SERVER_ERROR;
            }
        });
        ResponseEntity<ChatGptResponseDto> responseEntity = restTemplate.postForEntity(
                ChatGptConfig.URL,
                chatGptRequestDtoHttpEntity,
                ChatGptResponseDto.class);
        return responseEntity.getBody();
    }

    public ChatGptResponseDto askQuestion(String prompt) {
        return this.getResponse(
                this.buildHttpEntity(
                        new ChatGptRequestDto(
                                ChatGptConfig.MODEL,
                                prompt,
                                ChatGptConfig.MAX_TOKEN,
                                ChatGptConfig.TEMPERATURE,
                                ChatGptConfig.TOP_P
                        )
                )
        );
    }

    @Override
    public String makePrompt(String options) {
        StringBuilder sb = new StringBuilder();

        sb.append("list 형식으로 애완동물의 이름과 작명이유를 3가지 작성하시오.");
        sb.append("이 글의 대상은 20~40대 입니다.");
        sb.append(options);
        sb.append("이 키워드를 활용해서 작성하시오.");
        sb.append("키워드는 작명 이유에만 사용하시오.");
        sb.append("이름 우측에 이름과 어울리는 이모지를 추가하시오.");
        sb.append("각 행의 양식은 1. 이름 (이모지) - 작명이유의 포멧으로만 나타내시오.");
        sb.append("작명이유의 어미는 반드시 '지었습니다.'로 끝나게 작성하시오.");
        sb.append("첫번째 이름이 나오기 전에 '.'을 사용하지 마시오.");
        return sb.toString();
    }
}
