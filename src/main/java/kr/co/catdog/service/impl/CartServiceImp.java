package kr.co.catdog.service.impl;

import kr.co.catdog.domain.CartVO;
import kr.co.catdog.dto.CartDTO;
import kr.co.catdog.mapper.CartMapper;
import kr.co.catdog.service.CartService;
import kr.co.catdog.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImp implements CartService {
    private final CartMapper cartMapper;
    private final ModelMapper modelMapper;
    private final ShopService shopService;

    @Override
    public List<CartDTO> findById(String user_id) {
        List<CartVO> cartVOList = cartMapper.findById(user_id);

        List<CartDTO> cartDTOList = new ArrayList<>();
        cartVOList.forEach(cartVO -> {
            CartDTO cartDTO = modelMapper.map(cartVO, CartDTO.class);
            cartDTO.setProductDTO(shopService.findById(cartDTO.getProduct_no()));
            cartDTOList.add(cartDTO);
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

    @Override
    public int deleteAll(String user_id) {
        List<CartVO> cartVOList = cartMapper.findById(user_id);

        for (CartVO cartVO : cartVOList) {
            int result = cartMapper.delete(cartVO.getCart_no());
            if (result < 1) {
                return 0;
            }
        }

        return 1;
    }
}
