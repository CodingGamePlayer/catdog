package kr.co.catdog.mapper;

import kr.co.catdog.domain.ProductVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<ProductVO> selectAll();
    ProductVO findById(ProductVO productVO);
    int insert(ProductVO productVO);
    int update(ProductVO productVO);
    int delete(ProductVO productVO);



}
