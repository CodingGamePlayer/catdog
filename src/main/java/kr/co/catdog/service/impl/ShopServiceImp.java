package kr.co.catdog.service.impl;

import kr.co.catdog.domain.MediaVO;
import kr.co.catdog.domain.ProductVO;
import kr.co.catdog.dto.ProductDTO;
import kr.co.catdog.mapper.MediaMapper;
import kr.co.catdog.mapper.ProductMapper;
import kr.co.catdog.service.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ShopServiceImp implements ShopService {

    private final ModelMapper modelMapper;
    private final ProductMapper productMapper;
    private final MediaMapper mediaMapper;
    @Value("${kr.co.catdog.upload.path}")
    private String upPath;

    @Override
    public List<ProductDTO> selectAll(ProductDTO productDTO) {
        List<ProductVO> productVOList = productMapper.selectAll(productDTO);
        List<ProductDTO> productDTOList = new ArrayList<>();
        productVOList.forEach(productVO -> {
            ProductDTO DTO = modelMapper.map(productVO,ProductDTO.class);
            DTO.setMediaVO(mediaMapper.thumbnail(DTO.getProduct_no()));
            productDTOList.add(DTO);
        });
        // gkgk
        return productDTOList;
    }

    @Override
    public List<ProductDTO> orderByReviewCount() {
        List<ProductVO> productVOList = productMapper.orderByReviewCount();
        List<ProductDTO> productDTOList = new ArrayList<>();
        productVOList.forEach(productVO -> {
            ProductDTO productDTO = modelMapper.map(productVO,ProductDTO.class);
            productDTO.setMediaVO(mediaMapper.thumbnail(productDTO.getProduct_no()));
            productDTOList.add(productDTO);
        });

        return productDTOList;
    }

    @Override
    public List<ProductDTO> orderByReviewScore() {
        List<ProductVO> productVOList = productMapper.orderByReviewScore();
        List<ProductDTO> productDTOList = new ArrayList<>();
        productVOList.forEach(productVO -> {
            ProductDTO productDTO = modelMapper.map(productVO,ProductDTO.class);
            productDTO.setMediaVO(mediaMapper.thumbnail(productDTO.getProduct_no()));
            productDTOList.add(productDTO);
        });
        return productDTOList;
    }

    @Override
    public ProductDTO findById(int product_no) {

        ProductVO productVO = productMapper.findById(product_no);
        ProductDTO productDTO = modelMapper.map(productVO, ProductDTO.class);

        productDTO.setMediaVOList(mediaMapper.findById(product_no));
        productDTO.setMediaVO(mediaMapper.thumbnail(product_no));

        return productDTO;
    }

    @Override
    public int insert(ProductDTO productDTO) {

        int result = productMapper.insert(productDTO);

        String[] fileArray = productDTO.getMedia_path();
        for (int i = 0; i < fileArray.length; i++) {
            MediaVO mediaVO = MediaVO.builder()
                    .product_no(productDTO.getProduct_no())
                    .media_path(fileArray[i])
                    .build();
            mediaMapper.insert(mediaVO);

        }
        return !(result > 0) ? 0 : 1;
    }

    @Override
    public int update(ProductDTO productDTO) {
        int result = productMapper.update(productDTO);

        List<MediaVO> mediaVOList = mediaMapper.findById(productDTO.getProduct_no());
        mediaVOList.forEach(mediaVO -> {
            mediaMapper.delete(mediaVO.getMedia_no());
        });

        String[] fileArray = productDTO.getMedia_path();
        for (int i = 0; i < fileArray.length; i++) {
            MediaVO mediaVO = MediaVO.builder()
                    .product_no(productDTO.getProduct_no())
                    .media_path(fileArray[i])
                    .build();
            mediaMapper.insert(mediaVO);

        }
        return !(result > 0) ? 0 : 1;
    }

    @Override
    public int delete(int product_no) {
        int result = productMapper.delete(product_no);
        return !(result > 0) ? 0 : 1;
    }

}
