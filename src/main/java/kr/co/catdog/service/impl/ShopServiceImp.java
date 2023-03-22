package kr.co.catdog.service.impl;

import kr.co.catdog.domain.CartVO;
import kr.co.catdog.domain.MediaVO;
import kr.co.catdog.domain.ProductVO;
import kr.co.catdog.dto.CartDTO;
import kr.co.catdog.dto.ProductDTO;
import kr.co.catdog.mapper.CartMapper;
import kr.co.catdog.mapper.CategoryMapper;
import kr.co.catdog.mapper.MediaMapper;
import kr.co.catdog.mapper.ProductMapper;
import kr.co.catdog.service.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ShopServiceImp implements ShopService {

    private final ModelMapper modelMapper;
    private final ProductMapper productMapper;
    private final CategoryMapper categoryMapper;
    private final MediaMapper mediaMapper;
    private final CartMapper cartMapper;
    @Value("${kr.co.catdog.upload.path}")
    private String upPath;

//  Start Product ------------------------------------------------------------------------
    @Override
    public List<ProductDTO> selectAll() {
        List<ProductVO> productVOList = productMapper.selectAll();
        List<ProductDTO> productDTOList = productVOList.stream()
                .map(productVO -> modelMapper.map(productVO, ProductDTO.class))
                .collect(Collectors.toList());

        return productDTOList;
    }

    @Override
    public ProductDTO findById(ProductDTO productDTO) {

        ProductVO productVO = productMapper.findById(productDTO);
        if (productVO == null){
            return null;
        }
        ProductDTO DTO = modelMapper.map(productVO, ProductDTO.class);
        DTO.setCategory1VOList(categoryMapper.selectCategory1());

        return DTO;
    }

    @Override
    public int insert(ProductDTO productDTO) {

        int result = productMapper.insert(productDTO);
        log.info(String.valueOf("ggg"+String.valueOf(productDTO)));

        productDTO.getFiles().forEach(multipartFile -> {
            String fileName = UUID.randomUUID().toString() + "_" + multipartFile.getOriginalFilename();
            String filePath = upPath+"\\"+ fileName;
            File dest = new File(filePath);
            try {
                multipartFile.transferTo(dest);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            productDTO.setMedia_path(fileName);

            mediaMapper.insert(productDTO);

        });

        return !(result>0)? 0 : 1;
    }
    @Override
    public ProductDTO insertCategory(){
        ProductDTO productDTO = ProductDTO.builder()
                .category1VOList(categoryMapper.selectCategory1())
                .build();
        return productDTO;
    }

    @Override
    public int update(ProductDTO productDTO) {
        int result = productMapper.update(productDTO);
        return !(result>0)? 0 : 1;
    }

    @Override
    public int delete(ProductDTO productDTO) {
        int result = productMapper.delete(productDTO);
        return !(result>0)? 0 : 1;
    }
//    End Product ------------------------------------------------------------------------


//    Start Cart ------------------------------------------------------------------------

    @Override
    public List<CartDTO> findById_Cart(String user_id){

        List<CartVO> cartVOList = cartMapper.findById(CartDTO.builder()
                .user_id(user_id).build());
        List<CartDTO>cartDTOList = cartVOList.stream()
                .map(cartVO -> modelMapper.map(cartVO,CartDTO.class))
                .collect(Collectors.toList());

        cartDTOList.forEach(cartDTO -> {
            ProductDTO productDTO = findById(ProductDTO.builder().product_no(cartDTO.getProduct_no()).build());
            if (productDTO == null){
                return;
            }
            cartDTO.setProduct_name(productDTO.getProduct_name());
            cartDTO.setProduct_price(productDTO.getProduct_price());
        });
        return cartDTOList;
    }

    @Override
    public int insert_Cart(CartDTO cartDTO){
        CartVO cartVO = cartMapper.findById_No(cartDTO);

        if (cartVO != null){
            cartDTO.setCart_quantity(cartVO.getCart_quantity()+cartDTO.getCart_quantity());
            int result = cartMapper.update(cartDTO);

            return !(result>0)? 0 : 1;
        }
        int result = cartMapper.insert(cartDTO);

        return !(result>0)? 0 : 1;
    }
    @Override
    public int update_Cart(CartDTO cartDTO){
        int result = cartMapper.update(cartDTO);

        return !(result>0)? 0 : 1;
    }
    @Override
    public int delete_Cart(CartDTO cartDTO){
        int result = cartMapper.delete(cartDTO);

        return !(result>0)? 0 : 1;
    }

//    End Cart ------------------------------------------------------------------------
}
