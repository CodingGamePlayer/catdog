package kr.co.catdog.service;

import kr.co.catdog.dto.CartDTO;
import kr.co.catdog.dto.ProductDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ShopService {
    List<ProductDTO> selectAll();
    ProductDTO findById(ProductDTO productDTO);
    int insert(ProductDTO productDTO);
    ProductDTO insertCategory();
    int update(ProductDTO productDTO);
    int delete(ProductDTO productDTO);


    List<CartDTO> findById_Cart(String user_id);
    int insert_Cart(CartDTO cartDTO);
    int update_Cart(CartDTO cartDTO);
    int delete_Cart(CartDTO cartDTO);
}
