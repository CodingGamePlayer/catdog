package kr.co.catdog.service.impl;

import kr.co.catdog.dto.OrderDTO;
import kr.co.catdog.mapper.OrderMapper;
import kr.co.catdog.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServieceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;

    @Override
    public List<OrderDTO> findById(String user_id) {

        return orderMapper.findById(user_id);
    }

    @Override
    public int delete(int order_no) {

        return orderMapper.delete(order_no);
    }
}
