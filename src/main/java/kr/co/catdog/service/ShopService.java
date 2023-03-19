package kr.co.catdog.service;

import kr.co.catdog.dto.ProductDTO;

import java.util.List;

public interface ShopService {
    List<ProductDTO> selectAll();
    ProductDTO findById(int product_no);
    int insert(ProductDTO productDTO);
    int update(ProductDTO productDTO);
    int delete(int product_no);
    ProductDTO insertCategory();
    int insertMedia(ProductDTO productDTO);
    int deleteMedia(ProductDTO productDTO);
}
