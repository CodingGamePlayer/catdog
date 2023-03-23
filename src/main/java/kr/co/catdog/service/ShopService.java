package kr.co.catdog.service;

import kr.co.catdog.dto.CartDTO;
import kr.co.catdog.dto.ProductDTO;

import java.util.List;

public interface ShopService {
    List<ProductDTO> selectAll();
    ProductDTO findById(int product_no);
    int insert(ProductDTO productDTO);
    int update(ProductDTO productDTO);
    int delete(int product_no);


    List<CartDTO> findById_Cart(String user_id);
    int insert_Cart(CartDTO cartDTO);
    int update_Cart(CartDTO cartDTO);
    int delete_Cart(int cart_no);
}
