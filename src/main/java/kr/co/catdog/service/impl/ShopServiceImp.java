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
    public ProductDTO findById(int product_no) {
        ProductVO VO = ProductVO.builder()
                .product_no(product_no)
                .build();
        ProductVO productVO = productMapper.findById(VO);
        ProductDTO productDTO = modelMapper.map(productVO, ProductDTO.class);
        productDTO.setCategory1VOList(categoryMapper.selectCategory1());

        return productDTO;
    }
    @Transactional
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
        int result = productMapper.update(modelMapper.map(productDTO, ProductVO.class));
        return !(result>0)? 0 : 1;
    }

    @Override
    public int delete(int product_no) {
        ProductVO productVO = ProductVO.builder()
                .product_no(product_no)
                .build();
        int result = productMapper.delete(productVO);
        return !(result>0)? 0 : 1;
    }
//    End Product ------------------------------------------------------------------------
    @Override
    public int insertMedia(ProductDTO productDTO){

//            productDTO.getMediaVOList().stream()
//                    .map(mediaVO -> {
//                        String filename = UUID.randomUUID().toString()+"_"+mediaVO.getMedia_path();
//                        Path savePath = Paths.get(uploadPath, filename);
//                        MediaVO mediaVO1 = MediaVO.builder()
//                                .product_no(mediaVO.getProduct_no())
//                                .media_path(mediaVO.getMedia_path())
//                                .build();
//                        try {
//                            productDTO.getFile().transferTo(savePath);
//                        }catch (IOException e){
//                            e.printStackTrace();
//                            return 0;
//                        }
//                        int result = mediaMapper.insert(mediaVO1);
//                        log.info(String.valueOf(result));
//
//                        return result;
//                    });

//            productDTO.getMediaVOList().forEach(
//                mediaVO -> {
//                    String filename = UUID.randomUUID().toString()+"_"+mediaVO.getMedia_path();
//                    Path savePath = Paths.get(uploadPath, filename);
//                    MediaVO mediaVO1 = MediaVO.builder()
//                            .product_no(mediaVO.getProduct_no())
//                            .media_path(mediaVO.getMedia_path())
//                            .build();
//                    try {
//                        productDTO.getFile().transferTo(savePath);
//                    }catch (IOException e){
//                        e.printStackTrace();
//                    }
//                    int result = mediaMapper.insert(mediaVO1);
//                    log.info(String.valueOf(result));
//                });
            return 0;

    }
    @Override
    public int deleteMedia(ProductDTO productDTO){
        int result = mediaMapper.delete(modelMapper.map(productDTO, ProductVO.class));
        return !(result>0)? 0 : 1;
    }
//    Start Cart ------------------------------------------------------------------------

    @Override
    public List<CartDTO> findById_Cart(String user_id){
        CartVO cartVO = CartVO.builder()
                .user_id(user_id)
                .build();
        List<CartVO> cartVOList = cartMapper.findById(cartVO);
        List<CartDTO>cartDTOList = cartVOList.stream()
                .map(cartVO1 -> modelMapper.map(cartVO1,CartDTO.class))
                .collect(Collectors.toList());

        cartDTOList.forEach(cartDTO -> {
            cartDTO.setProduct_name(findById(cartDTO.getProduct_no()).getProduct_name());
            cartDTO.setProduct_price(findById(cartDTO.getProduct_no()).getProduct_price());
        });
        return cartDTOList;
    }

    @Override
    public int insert_Cart(CartDTO cartDTO){
        CartVO cartVO = cartMapper.findById_No(modelMapper.map(cartDTO, CartVO.class));

        if (cartVO != null){
            CartDTO DTO = modelMapper.map(cartVO, CartDTO.class);
            DTO.setCart_quantity(DTO.getCart_quantity()+cartDTO.getCart_quantity());
            int result = cartMapper.update(modelMapper.map(DTO, CartVO.class));

            return !(result>0)? 0 : 1;
        }
        int result = cartMapper.insert(modelMapper.map(cartDTO, CartVO.class));

        return !(result>0)? 0 : 1;
    }
    @Override
    public int update_Cart(CartDTO cartDTO){
        int result = cartMapper.update(modelMapper.map(cartDTO, CartVO.class));
        return !(result>0)? 0 : 1;
    }
    @Override
    public int delete_Cart(int cart_no){
        CartVO cartVO = CartVO.builder()
                .cart_no(cart_no)
                .build();
        int result = cartMapper.delete(cartVO);
        return !(result>0)? 0 : 1;
    }

//    End Cart ------------------------------------------------------------------------
}
