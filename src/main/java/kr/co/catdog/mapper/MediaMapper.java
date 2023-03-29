package kr.co.catdog.mapper;

import kr.co.catdog.domain.MediaVO;
import kr.co.catdog.dto.ProductDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface MediaMapper {
    List<MediaVO> findById(int product_no);

    MediaVO thumbnail(int product_no);

    int insert(MediaVO mediaVO);

    int delete(int media_no);

}
