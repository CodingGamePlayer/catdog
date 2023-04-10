package kr.co.catdog.service;

import kr.co.catdog.dto.CartDTO;

import java.util.List;

public interface CartService {

    List<CartDTO> findById(String user_id);
    int insert(CartDTO cartDTO);
    int update(CartDTO cartDTO);
    int delete(int cart_no);
    int deleteAll(String user_id);
}
