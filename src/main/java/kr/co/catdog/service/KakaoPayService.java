package kr.co.catdog.service;

import kr.co.catdog.dto.KakaoPayApproveResponseDTO;
import kr.co.catdog.dto.KakaoPayResponseDTO;
import kr.co.catdog.dto.OrderDTO;

public interface KakaoPayService {

    public KakaoPayResponseDTO kakaoPayReady(OrderDTO orderDTO);

    public KakaoPayApproveResponseDTO ApproveResponse(OrderDTO orderDTO);
}
