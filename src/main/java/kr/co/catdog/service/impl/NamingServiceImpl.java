package kr.co.catdog.service.impl;

import kr.co.catdog.service.NamingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NamingServiceImpl implements NamingService {
    @Override
    public String makePrompt(String[] options) {
        StringBuilder sb = new StringBuilder();
        sb.append(options[0]);  // 강아지 / 고양이
        sb.append(options[1]);  // 성격 온순한, 귀여운, 활달한, 듬직한, 엉뚱한
        sb.append(options[2]);  // 갯수 1~5개
        return sb.toString();
    }
}
