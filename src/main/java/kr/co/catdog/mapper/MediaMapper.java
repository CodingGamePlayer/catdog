package kr.co.catdog.mapper;

import kr.co.catdog.domain.MediaVO;
import kr.co.catdog.domain.ProductVO;
import kr.co.catdog.dto.ProductDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface MediaMapper {
    List<MediaVO> findById(ProductVO productVO);
    int insert(ProductDTO productDTO);
    int delete(ProductVO productVO);

}
