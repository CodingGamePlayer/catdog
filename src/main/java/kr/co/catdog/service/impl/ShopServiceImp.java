package kr.co.catdog.service.impl;

import kr.co.catdog.domain.ProductVO;
import kr.co.catdog.domain.ReviewVO;
import kr.co.catdog.dto.ProductDTO;
import kr.co.catdog.dto.ReviewDTO;
import kr.co.catdog.mapper.MediaMapper;
import kr.co.catdog.mapper.ProductMapper;
import kr.co.catdog.mapper.ReviewMapper;
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
    private final ReviewMapper reviewMapper;
    @Value("${kr.co.catdog.upload.path}")
    private String upPath;

    @Override
    public List<ProductDTO> selectAll() {
        List<ProductVO> productVOList = productMapper.selectAll();
        List<ProductDTO> productDTOList = productVOList.stream()
                .map(productVO -> modelMapper.map(productVO, ProductDTO.class))
                .collect(Collectors.toList());

        return productDTOList;
    }

    @Override
    public List<ProductDTO> orderByReviewCount() {
        List<ProductDTO> productDTOList = selectAll();
        productDTOList.forEach(productDTO -> {
            productDTO.setReviewCount(reviewMapper.selectAll(productDTO.getProduct_no()).size());
        });

        return productDTOList;
    }

    @Override
    public List<ProductDTO> orderByReviewScore() {
        List<ReviewVO> reviewVOList = reviewMapper.orderByReviewScore();

        List<ProductDTO> productDTOList = reviewVOList.stream()
                .map(reviewVO -> findById(reviewVO.getProduct_no()))
                .collect(Collectors.toList());
        return productDTOList;
    }

    @Override
    public ProductDTO findById(int product_no) {

        ProductVO productVO = productMapper.findById(product_no);
        ProductDTO productDTO = modelMapper.map(productVO, ProductDTO.class);

        productDTO.setMediaVOList(mediaMapper.findById(product_no));

        return productDTO;
    }

    @Override
    public int insert(ProductDTO productDTO) {

        int result = productMapper.insert(productDTO);

        productDTO.getFiles().forEach(multipartFile -> {

            if (!multipartFile.getOriginalFilename().equals("")) {

                String fileName = UUID.randomUUID().toString() + "_" + multipartFile.getOriginalFilename();
                String filePath = upPath + "\\" + fileName;
                File dest = new File(filePath);
                try {
                    multipartFile.transferTo(dest);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                productDTO.setMedia_path(fileName);

                mediaMapper.insert(productDTO);
            }
        });

        return !(result > 0) ? 0 : 1;
    }

    @Override
    public int update(ProductDTO productDTO) {
        int result = productMapper.update(productDTO);
        return !(result > 0) ? 0 : 1;
    }

    @Override
    public int delete(int product_no) {
        int result = productMapper.delete(product_no);
        return !(result > 0) ? 0 : 1;
    }

}
