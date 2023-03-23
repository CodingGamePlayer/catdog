package kr.co.catdog.mapper;

import kr.co.catdog.domain.ProductVO;
import kr.co.catdog.dto.ProductDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<ProductVO> selectAll();
    ProductVO findById(int product_no);
    int insert(ProductDTO productDTO);
    int update(ProductDTO productDTO);
    int delete(ProductDTO productDTO);



}
