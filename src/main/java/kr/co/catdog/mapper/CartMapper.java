package kr.co.catdog.mapper;

import kr.co.catdog.domain.CartVO;
import kr.co.catdog.dto.CartDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartMapper {
    List<CartVO> findById(String user_id);
    CartVO findById_No(CartDTO cartDTO);
    int insert(CartDTO cartDTO);
    int update(CartDTO cartDTO);
    int delete(int cart_no);
    int deleteAll(String user_id);

}
