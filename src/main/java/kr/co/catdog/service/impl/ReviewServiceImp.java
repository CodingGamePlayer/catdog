package kr.co.catdog.service.impl;

import kr.co.catdog.domain.ReviewVO;
import kr.co.catdog.dto.PageDTO;
import kr.co.catdog.dto.ReviewDTO;
import kr.co.catdog.mapper.ReviewMapper;
import kr.co.catdog.mapper.UserMapper;
import kr.co.catdog.service.ReviewService;
import kr.co.catdog.service.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewServiceImp implements ReviewService {
    private final ReviewMapper reviewMapper;
    private final ModelMapper modelMapper;
    private final UserMapper userMapper;
    private final ShopService shopService;

    @Override
    public List<ReviewDTO> selectAll(PageDTO pageDTO) {
        List<ReviewVO> reviewVOList = reviewMapper.selectAll(pageDTO);

        List<ReviewDTO> reviewDTOList = new ArrayList<>();
        reviewVOList.forEach(reviewVO -> {
            ReviewDTO reviewDTO = modelMapper.map(reviewVO, ReviewDTO.class);
            reviewDTO.setUser_image(userMapper.imgFindById(reviewDTO.getUser_id()).getUser_image());
            reviewDTOList.add(reviewDTO);
        });

        return reviewDTOList;
    }

    @Override
    public List<ReviewDTO> findByUserid(String user_id) {
        List<ReviewVO> reviewVOList = reviewMapper.findByUserid(user_id);

        List<ReviewDTO> reviewDTOList = new ArrayList<>();
        if (reviewVOList != null) {
            reviewVOList.forEach(reviewVO -> {
                ReviewDTO reviewDTO = modelMapper.map(reviewVO, ReviewDTO.class);
                reviewDTO.setProductDTO(shopService.findById(reviewDTO.getProduct_no()));
                reviewDTOList.add(reviewDTO);
            });
        }

        return reviewDTOList;
    }

    @Override
    public int insert(ReviewDTO reviewDTO) {
        int result = reviewMapper.insert(reviewDTO);

        return !(result > 0) ? 0 : 1;
    }

    @Override
    public int delete(int review_no) {
        int result = reviewMapper.delete(review_no);

        return !(result > 0) ? 0 : 1;
    }
}
