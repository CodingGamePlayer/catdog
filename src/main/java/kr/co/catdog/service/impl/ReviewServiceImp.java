package kr.co.catdog.service.impl;

import kr.co.catdog.domain.ReviewVO;
import kr.co.catdog.dto.ReviewDTO;
import kr.co.catdog.mapper.ReviewMapper;
import kr.co.catdog.mapper.UserMapper;
import kr.co.catdog.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImp implements ReviewService {
    private ReviewMapper reviewMapper;
    private ModelMapper modelMapper;
    private UserMapper userMapper;

    @Override
    public List<ReviewDTO> selectAll(int product_no) {
        List<ReviewVO> reviewVOList = reviewMapper.selectAll(product_no);
        if (!reviewVOList.isEmpty()) {
            List<ReviewDTO> reviewDTOList = reviewVOList.stream()
                    .map(reviewVO -> modelMapper.map(reviewVO, ReviewDTO.class))
                    .collect(Collectors.toList());
            reviewDTOList.forEach(reviewDTO -> {
                reviewDTO.setUser_image(userMapper.imgFindById(reviewDTO.getUser_id()).getUser_image());
            });
            return reviewDTOList;
        }
        return null;
    }

    @Override
    public int insert(ReviewDTO reviewDTO) {
        int result = reviewMapper.insert(reviewDTO);
        return !(result > 0) ? 0 : 1;
    }

    @Override
    public int delete(int review_no) {
        int result = reviewMapper.delete();

        return !(result > 0) ? 0 : 1;
    }
}
