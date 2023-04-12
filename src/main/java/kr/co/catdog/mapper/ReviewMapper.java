package kr.co.catdog.mapper;

import kr.co.catdog.domain.ReviewVO;
import kr.co.catdog.dto.PageDTO;
import kr.co.catdog.dto.ReviewDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {
    List<ReviewVO> selectAll(PageDTO pageDTO);
    List<ReviewVO> findByUserid(String user_id);
    int insert(ReviewDTO reviewDTO);

    int delete(int review_no);

}
