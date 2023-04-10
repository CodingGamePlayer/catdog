package kr.co.catdog.service;

import kr.co.catdog.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    List<OrderDTO> findById(String user_id);

    int delete(int order_no);
}
