package kr.co.catdog.service.impl;

import kr.co.catdog.domain.CartVO;
import kr.co.catdog.dto.CartDTO;
import kr.co.catdog.mapper.CartMapper;
import kr.co.catdog.mapper.ProductMapper;
import kr.co.catdog.service.CartService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImp implements CartService {
    private final CartMapper cartMapper;
    private final ModelMapper modelMapper;
    private final ProductMapper productMapper;

    @Override
    public List<CartDTO> findById(String user_id) {

        List<CartVO> cartVOList = cartMapper.findById(user_id);
        List<CartDTO> cartDTOList = cartVOList.stream()
                .map(cartVO -> modelMapper.map(cartVO, CartDTO.class))
                .collect(Collectors.toList());

        cartDTOList.forEach(cartDTO -> {
            cartDTO.setProductVO(productMapper.findById(cartDTO.getProduct_no()));
        });
        return cartDTOList;
    }

    @Override
    public int insert(CartDTO cartDTO) {
        CartVO cartVO = cartMapper.findById_No(cartDTO);

        if (cartVO != null) {
            cartDTO.setCart_no(cartVO.getCart_no());
            cartDTO.setCart_quantity(cartVO.getCart_quantity() + cartDTO.getCart_quantity());
            int result = cartMapper.update(cartDTO);

            return !(result > 0) ? 0 : 1;
        }
        int result = cartMapper.insert(cartDTO);

        return !(result > 0) ? 0 : 1;
    }

    @Override
    public int update(CartDTO cartDTO) {
        int result = cartMapper.update(cartDTO);

        return !(result > 0) ? 0 : 1;
    }

    @Override
    public int delete(int cart_no) {
        int result = cartMapper.delete(cart_no);

        return !(result > 0) ? 0 : 1;
    }
}
