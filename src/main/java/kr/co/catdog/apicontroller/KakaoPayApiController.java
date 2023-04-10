package kr.co.catdog.apicontroller;

import kr.co.catdog.dto.KakaoPayApproveResponseDTO;
import kr.co.catdog.dto.KakaoPayResponseDTO;
import kr.co.catdog.dto.OrderDTO;
import kr.co.catdog.service.KakaoPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@Slf4j
public class KakaoPayApiController {
    @Autowired
    private KakaoPayService kakaoPayService;

    @PostMapping("api/kakao/ready")
    public KakaoPayResponseDTO readyToKakaoPay(@RequestBody OrderDTO orderDTO, HttpServletRequest request){
        log.info("kakao 호출 됨");
        log.info("kakao order_price : "+orderDTO.getOrder_price());
        String serverName = request.getServerName();
        log.info("serverName : "+serverName);
        orderDTO.setServer_name(serverName);

        return kakaoPayService.kakaoPayReady(orderDTO);
    }

    @GetMapping("api/kakao/success")
    public ResponseEntity afterPayRequest(@RequestParam("pg_token") String pg_token, HttpServletRequest request){
        HttpSession session = request.getSession();
        String user_id = (String) session.getAttribute("session_id");
        log.info("pg_token : " + pg_token);
        OrderDTO orderDTO = OrderDTO.builder()
                .user_id(user_id)
                .pg_token(pg_token)
                .build();

        KakaoPayApproveResponseDTO kakaoPayApproveResponseDTO = kakaoPayService.ApproveResponse(orderDTO);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.TEXT_HTML);
        String responseBody = "<script>window.opener.afeterClosePay();window.close();</script>";

        return new ResponseEntity<>(responseBody, responseHeaders, HttpStatus.OK);

    }

    @GetMapping("api/kakao/cancel")
    public ResponseEntity cancel() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.TEXT_HTML);
        String responseBody = "<script>window.close();</script>";

        return new ResponseEntity<>(responseBody,responseHeaders,HttpStatus.BAD_REQUEST);
    }

    @GetMapping("api/kakao/fail")
    public ResponseEntity fail() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.TEXT_HTML);
        String responseBody = "<script>window.close();</script>";

        return new ResponseEntity<>(responseBody,responseHeaders,HttpStatus.BAD_REQUEST);
    }
}
