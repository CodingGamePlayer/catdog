package kr.co.catdog.mapper;

import kr.co.catdog.domain.ProductVO;
import kr.co.catdog.dto.ProductDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<ProductVO> selectAll();
    ProductVO findById(ProductVO productVO);
    int insert(ProductDTO productDTO);
    int update(ProductVO productVO);
    int delete(ProductVO productVO);



}
