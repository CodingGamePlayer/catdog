package kr.co.catdog.mapper;

import kr.co.catdog.domain.CartVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartMapper {
    List<CartVO> findById(CartVO cartVO);
    int insert(CartVO cartVO);
    int update(CartVO cartVO);
    int delete(CartVO cartVO);
}
