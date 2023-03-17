package kr.co.catdog.controller;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import kr.co.catdog.dto.KaKaoMapResponse;
import org.json.simple.JSONObject;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.StringReader;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

@RestController
public class KakaoApi {

    private final String url = "https://dapi.kakao.com/v2/local/geo/coord2regioncode.json";
    private final String key = "20752786a299122fc1b526bb8dd4dfa8";

    @GetMapping("/kakao")
    public void callApi() {
        String x = "190562.90915523";
        String y = "189333.017062573";
        String url = "https://dapi.kakao.com/v2/local/geo/coord2regioncode.json";

        StringBuilder sb = new StringBuilder();
        sb.append(url);
        sb.append("?x=");
        sb.append(x);
        sb.append("&y=");
        sb.append(y);
        sb.append("&input_coord=TM");
        sb.toString();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "KakaoAK "+key);

        HttpEntity<?> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<Map> response = restTemplate.exchange(sb.toString(), HttpMethod.GET, entity, Map.class);

        System.out.println(response.getBody());

        System.out.println(response.getBody().get("meta"));
        ArrayList<KaKaoMapResponse.Document> doc = (ArrayList<KaKaoMapResponse.Document>)response.getBody().get("documents");
        System.out.println(doc.get(0).getClass());



        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new StringReader(response.toString()));
        reader.setLenient(true);


        // KaKaoMapResponse mapped_data = gson.fromJson(reader, KaKaoMapResponse.class);



        // when

        // then
        /*
        Gson gson = new Gson();
        KaKaoMapResponse mapped_data = gson.fromJson(response.getBody().toString(),KaKaoMapResponse.class);
        String target = mapped_data.documents.get(0).address_name;
        System.out.println(target);
        */
    }

}
