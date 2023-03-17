package kr.co.catdog;

import com.google.gson.Gson;
import kr.co.catdog.dto.GovermentDTO;
import kr.co.catdog.dto.HospitalDTO;
import kr.co.catdog.dto.KaKaoMapResponse;
import kr.co.catdog.mapper.HospitalMapper;
import kr.co.catdog.service.GoverMentService;
import kr.co.catdog.service.HospitalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
@Slf4j
public class KakaoMapTest {

    @Autowired
    GoverMentService goverMentService;
    @Autowired
    HospitalService hospitalService;

    @Test
    @DisplayName("return location input lat, lon")
    public void getLocationTest() throws Exception {
        Double x = 190562.90915523;
        Double y = 189333.017062573;
        /*
        String url = "https://dapi.kakao.com/v2/local/geo/coord2regioncode.json";
        double x = 126.9863309;
        double y = 37.563398;
        */
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://dapi.kakao.com/v2/local/geo/coord2regioncode.json";
        UriComponents uri = UriComponentsBuilder.newInstance()
                .fromHttpUrl(url)
                .queryParam("x",x)
                .queryParam("y",y)
                .queryParam("input_coord","TM")
                .build();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", String.format("KakaoAK 20752786a299122fc1b526bb8dd4dfa8"));

        // when
        HttpEntity requestMessage = new HttpEntity(httpHeaders);
        ResponseEntity response = restTemplate.exchange(
                uri.toUriString(),
                HttpMethod.GET,
                requestMessage,
                String.class);
        // then
        // 해당 JObject와 Response 객체간의 매핑
        Gson gson = new Gson();
        KaKaoMapResponse mapped_data = gson.fromJson(response.getBody().toString(),KaKaoMapResponse.class);
        String firstName = mapped_data.documents.get(0).address_name;
        String secondName = mapped_data.documents.get(1).address_name;
        String target = mapped_data.documents.get(0).region_type;
        System.out.println(firstName);
        System.out.println(secondName);
        System.out.println(target);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }


    @Test
    @DisplayName("bring goverment data")
    public void govermentDBTest() {
        // goverMentService.getAll();
        Assert.assertNotNull(goverMentService.getAll());
        List<GovermentDTO> all = goverMentService.getAll();

        IntStream.rangeClosed(1, 10).forEach(count ->{
            System.out.println("longitude : " + all.get(count).getLongitude() + " latitude : "+ all.get(count).getLatitude());
        });
    }

    @Test
    @Transactional
    @DisplayName("convert goverment data to kakao location")
    public void convertDBTest() {

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://dapi.kakao.com/v2/local/geo/coord2regioncode.json";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", String.format("KakaoAK 20752786a299122fc1b526bb8dd4dfa8"));

        List<GovermentDTO> all = goverMentService.getAll();
        List<HospitalDTO> hospitalDTOS = new ArrayList<>();
        int bCnt = 0;
        int hCnt = 0;
        for(int idx = 0; idx < all.size(); idx++) {
            Double x = all.get(idx).getLongitude();
            Double y = all.get(idx).getLatitude();

            UriComponents uri = UriComponentsBuilder.newInstance()
                    .fromHttpUrl(url)
                    .queryParam("x",x)
                    .queryParam("y",y)
                    .queryParam("input_coord","TM")
                    .build();

            // when
            HttpEntity requestMessage = new HttpEntity(httpHeaders);
            ResponseEntity response = restTemplate.exchange(
                    uri.toUriString(),
                    HttpMethod.GET,
                    requestMessage,
                    String.class);

            // then
            // 해당 JObject와 Response 객체간의 매핑
            Gson gson = new Gson();
            KaKaoMapResponse mapped_data = gson.fromJson(response.getBody().toString(),KaKaoMapResponse.class);
            hospitalDTOS.add(
                    new HospitalDTO(all.get(idx).getHospital_id(), all.get(idx).getBusiness_name(), all.get(idx).getTel(),
                            all.get(idx).getLongitude(), all.get(idx).getLatitude(),
                            mapped_data.getDocuments().get(0).getRegion_type(), mapped_data.getDocuments().get(0).getAddress_name(),
                            mapped_data.getDocuments().get(0).getRegion_1depth_name(), mapped_data.getDocuments().get(0).getRegion_2depth_name(),
                            mapped_data.getDocuments().get(0).getRegion_3depth_name(), mapped_data.getDocuments().get(0).getRegion_4depth_name(),
                            mapped_data.getDocuments().get(0).getCode(), mapped_data.getDocuments().get(0).x, mapped_data.getDocuments().get(0).y,
                            mapped_data.getDocuments().get(1).getRegion_type(), mapped_data.getDocuments().get(1).getAddress_name(),
                            mapped_data.getDocuments().get(1).getRegion_1depth_name(), mapped_data.getDocuments().get(1).getRegion_2depth_name(),
                            mapped_data.getDocuments().get(1).getRegion_3depth_name(), mapped_data.getDocuments().get(1).getRegion_4depth_name(),
                            mapped_data.getDocuments().get(1).getCode(), mapped_data.getDocuments().get(1).x, mapped_data.getDocuments().get(1).y));
            assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        }

        for(int idx = 0; idx < hospitalDTOS.size(); idx++) {
            hospitalService.insert(hospitalDTOS.get(idx));
        }

    }
}
