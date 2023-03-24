package kr.co.catdog.apicontroller;

import kr.co.catdog.dto.HospitalDTO;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class NamingApiController {
    private NamingService namingService;

    @Value("${chatgpt.api.key}")
    private String apiKey;
    private static final String ENDPOINT = "https://api.openai.com/v1/completions";

    @GetMapping("/api/user/naming/place")
    public List<Map<String, Object>> mapList(@RequestParam String[] options){

        String prompt;
        prompt = namingService.makePrompt(options);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model","text-davinci-003");    // 모델정보 선택
        requestBody.put("prompt", prompt);              // 프롬프트 입력 (페이지로 부터 -> 넘어온 값이 없다면 기본적인 생성문을 넘김
        requestBody.put("temperature", 1.0f);           // 정확도 및 상세 0.0이 될수록 자세하고 상세 2.0이 날림
        requestBody.put("max_tokens", 1000);            // 반환할 문자열의 크기를 결정짓는 토큰의 갯수

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response = restTemplate.postForEntity(ENDPOINT, requestEntity, Map.class);
        log.info(response.toString());

        JSONObject jsonObj;
        JSONArray jsonArr = new JSONArray();

        return jsonArr;
    }

    public NamingApiController(NamingService namingService) {
        this.namingService = namingService;
    }
}
