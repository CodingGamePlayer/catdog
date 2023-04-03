package kr.co.catdog.mapper;

import kr.co.catdog.domain.ProductVO;
import kr.co.catdog.dto.ProductDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<ProductVO> selectAll(ProductDTO productDTO);
    ProductVO findById(int product_no);
    int insert(ProductDTO productDTO);
    int update(ProductDTO productDTO);
    int delete(int product_no);

    List<ProductVO> orderByReviewCount();
    List<ProductVO> orderByReviewScore();



}
