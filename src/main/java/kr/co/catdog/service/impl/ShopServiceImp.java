package kr.co.catdog.service.impl;

import kr.co.catdog.domain.MediaVO;
import kr.co.catdog.domain.ProductVO;
import kr.co.catdog.dto.ProductDTO;
import kr.co.catdog.mapper.CategoryMapper;
import kr.co.catdog.mapper.MediaMapper;
import kr.co.catdog.mapper.ProductMapper;
import kr.co.catdog.service.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
    @Value("${kr.co.catdog.upload.path}")
    private String uploadPath;

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
        ProductDTO productDTO = ProductDTO.builder()
                .product_no(product_no)
                .build();
        ProductVO productVO = productMapper.findById(modelMapper.map(productDTO, ProductVO.class));
        ProductDTO dto = modelMapper.map(productVO, ProductDTO.class);
        dto.setCategory1VOList(categoryMapper.selectCategory1());
//        dto.setMediaVOList(mediaMapper.findById(productVO));

        return dto;
    }

    @Override
    public int insert(ProductDTO productDTO) {
        int result = productMapper.insert(modelMapper.map(productDTO, ProductVO.class));
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
        ProductDTO productDTO = ProductDTO.builder()
                .product_no(product_no)
                .build();
        int result = productMapper.delete(modelMapper.map(productDTO, ProductVO.class));
        return !(result>0)? 0 : 1;
    }
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
}
