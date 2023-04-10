package kr.co.catdog.service.impl;

import kr.co.catdog.dto.KakaoPayApproveResponseDTO;
import kr.co.catdog.dto.KakaoPayResponseDTO;
import kr.co.catdog.dto.OrderDTO;
import kr.co.catdog.mapper.OrderMapper;
import kr.co.catdog.service.KakaoPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class KakaoPayServiceImpl implements KakaoPayService {

    @Autowired
    private OrderMapper orderMapper;
    private final String admin_Key = "1ff03e4821fe3f36b9ae7e45ac18a9e2";
    private final String cid = "TC0ONETIME";
    private KakaoPayResponseDTO kakaoPayResponseDTO;
    @Override
    public KakaoPayResponseDTO kakaoPayReady(OrderDTO orderDTO) {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", cid);
        parameters.add("partner_order_id","주문번호");
        parameters.add("partner_user_id", orderDTO.getUser_id());
        parameters.add("item_name","상품명");
        parameters.add("quantity","2");
        parameters.add("total_amount",Integer.toString(orderDTO.getOrder_price()));
        parameters.add("tax_free_amount","0");
        if(orderDTO.getServer_name().equals("localhost")) {
            parameters.add("approval_url", "http://localhost:8080/api/kakao/success");
            parameters.add("cancel_url", "http://localhost:8080/api/kakao/cancel");
            parameters.add("fail_url", "http://localhost:8080/api/kakao/fail");
        }else {
            parameters.add("approval_url", "https://catdogforest.shop/api/kakao/success");
            parameters.add("cancel_url", "https://catdogforest.shop/kakao/cancel");
            parameters.add("fail_url", "https://catdogforest.shop/api/kakao/fail");
        }

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

        RestTemplate restTemplate = new RestTemplate();

        kakaoPayResponseDTO = restTemplate.postForObject(
                "https://kapi.kakao.com/v1/payment/ready",
                requestEntity,
                KakaoPayResponseDTO.class);
        return kakaoPayResponseDTO;
    }

    @Override
    public KakaoPayApproveResponseDTO ApproveResponse(OrderDTO orderDTO) {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", cid);
        parameters.add("tid", kakaoPayResponseDTO.getTid());
        parameters.add("partner_order_id","주문번호");
        parameters.add("partner_user_id", orderDTO.getUser_id());
        parameters.add("pg_token", orderDTO.getPg_token());

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

        RestTemplate restTemplate = new RestTemplate();
        KakaoPayApproveResponseDTO kakaoPayApproveResponseDTO = restTemplate.postForObject(
                "https://kapi.kakao.com/v1/payment/approve",
                requestEntity,
                KakaoPayApproveResponseDTO.class);
        orderDTO.setOrder_price(kakaoPayApproveResponseDTO.getAmount().getTotal());
        orderMapper.register(orderDTO);

        return kakaoPayApproveResponseDTO;
    }


    private HttpHeaders getHeaders(){
        HttpHeaders httpHeaders= new HttpHeaders();

        String auth = "KakaoAK " + admin_Key;

        httpHeaders.set("Authorization", auth);
        httpHeaders.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        return httpHeaders;
    }
}
