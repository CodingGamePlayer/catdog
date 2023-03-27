package kr.co.catdog.service;

import kr.co.catdog.dto.ChatGptRequestDto;
import kr.co.catdog.dto.ChatGptResponseDto;
import kr.co.catdog.dto.QuestionRequestDto;
import org.springframework.http.HttpEntity;

public interface ChatGptService {
    public HttpEntity<ChatGptRequestDto> buildHttpEntity(ChatGptRequestDto requestDto);
    public ChatGptResponseDto getResponse(HttpEntity<ChatGptRequestDto> chatGptRequestDtoHttpEntity);
    public ChatGptResponseDto askQuestion(QuestionRequestDto requestDto);
}
