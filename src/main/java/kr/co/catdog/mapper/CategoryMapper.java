package kr.co.catdog.mapper;

import kr.co.catdog.domain.Category1VO;
import kr.co.catdog.domain.Category2VO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    List<Category1VO> selectCategory1();
    List<Category2VO> selectCategory2();
}
