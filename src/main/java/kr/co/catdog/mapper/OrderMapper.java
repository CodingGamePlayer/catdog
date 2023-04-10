package kr.co.catdog.mapper;

import kr.co.catdog.dto.OrderDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {

    int register(OrderDTO orderDTO);

    List<OrderDTO> findById(String user_id);

    int delete(int onder_no);
}
